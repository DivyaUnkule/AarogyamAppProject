package com.app.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;


@Component
//@Slf4j
public class JwtUtils {
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Value("${SECRET_KEY}")
    private String jwtSecret;

    @Value("${EXP_TIMEOUT}")
    private int jwtExpirationMs;

    private Key key;

    @PostConstruct
    public void init() {
    	this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateJwtToken(Authentication authentication) {
        log.info("Generating JWT token for: {}", authentication);
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
                .claim("user_id", userPrincipal.getUser().getId())
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    
    


    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }

    public Claims validateJwtToken(String jwtToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // Check if the token has expired
            if (claims.getExpiration().before(new Date())) {
                throw new ExpiredJwtException(null, claims, "JWT token is expired");
            }

            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token", e);
            throw e; // Or handle according to your needs
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
            throw e; // Or handle according to your needs
        }
    }
    private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        String authString = (String) claims.get("authorities");
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
    }

    public Long getUserIdFromJwtToken(Claims claims) {
        return claims.get("user_id", Long.class);
    }

    public Authentication populateAuthenticationTokenFromJWT(String jwt) {
        Claims payloadClaims = validateJwtToken(jwt);
        String email = getUserNameFromJwtToken(payloadClaims);
        List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
        Long userId = getUserIdFromJwtToken(payloadClaims);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, userId, authorities);
        log.info("Is authenticated: {}", token.isAuthenticated());
        return token;
    }
}
