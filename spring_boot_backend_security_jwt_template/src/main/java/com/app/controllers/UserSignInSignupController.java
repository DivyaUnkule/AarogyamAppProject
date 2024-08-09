package com.app.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.dto.Signup;
import com.app.dto.UserRegResponse;
import com.app.entities.Login;
import com.app.enums.Status;
import com.app.security.CustomUserDetails;
import com.app.security.JwtUtils;
import com.app.services.IUserService;

@RestController
@RequestMapping("/users")
public class UserSignInSignupController {
	@Autowired
	private IUserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;

	// sign up
	/*
	 * URL - http://host:port/users/signup Method - POST request payload : sign up
	 * DTO (user details) resp : In case of success : Auth Resp DTO :mesg + JWT
	 * token + SC 201 In case of failure : SC 401
	 * 
	 */
	@PostMapping(value = "/signup", produces = "application/json")
	public ResponseEntity<UserRegResponse> userSignup(@RequestBody @Valid Signup dto) throws IOException {
	    System.out.println("in sign up " + dto);
	    UserRegResponse response = userService.userRegistration(dto);
	    return ResponseEntity.status(HttpStatus.CREATED)
	                         .contentType(MediaType.APPLICATION_JSON)
	                         .body(response);
	}

	/*
	 * URL - http://host:port/users/signin Method - POST request payload : Auth req
	 * DTO : email n password resp : In case of success : Auth Resp DTO :mesg + JWT
	 * token + SC 201 In case of failure : SC 401
	 * 
	 */
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid SigninRequest loginRequest) {
	    try {
	        // Perform authentication
	        Authentication authentication = authMgr.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginRequest.getEmail(),
	                loginRequest.getPassword()
	            )
	        );

	        // Set authentication context
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Generate JWT token
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        // Fetch user details
	        Login user = userService.fetchByEmail(loginRequest.getEmail());

	        // Check if user is active
	        if (user.getStatus() == Status.ACTIVE) {
	            return ResponseEntity.ok(new SigninResponse(jwt, user));
	        } else {
	            return ResponseEntity.badRequest().body("User is not active.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
	    }
	}

	
    
	
	@PostMapping(value="/{Id}/image_upload",consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImageToServerSideFolder(@PathVariable Long Id,
			@RequestParam MultipartFile imageFile) throws IOException{
		System.out.println("in upload img " + Id + " " + imageFile.getOriginalFilename());
		return new ResponseEntity<>(userService.uploadImage(Id, imageFile), HttpStatus.CREATED);
	}
	
	

}
