package com.app.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.app.entities.Login;
import com.app.entities.Login;

import lombok.AllArgsConstructor;

public class CustomUserDetails implements UserDetails {
	private Login user;
	
	public CustomUserDetails(Login user) {
		super();
		this.user = user;
	}
		
	public Login getUser() {
		return user;
	}

	public void setUser(Login user) {
		this.user = user;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		var roleList = user.getUserRoles()
						.stream()
						.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
						.collect(Collectors.toList());
		return roleList;
	}
	 
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
