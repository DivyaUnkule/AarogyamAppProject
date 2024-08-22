package com.app.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ResetPassword;
import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.dto.Signup;
import com.app.dto.UserRegResponse;
import com.app.entities.Login;
import com.app.enums.Role;
import com.app.security.CustomUserDetails;
import com.app.security.JwtUtils;
import com.app.services.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserSignInSignupController {
	@Autowired
	private IUserService userService;

	@Autowired
	private JwtUtils jwtUtils;
	//dep 
			@Autowired
			private PasswordEncoder encoder;
	
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
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid SigninRequest request) {
	    System.out.println("in sign in" + request);
	    
	    // 1. Create a token to store unverified user email and password
	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
	            request.getPassword());
	    
	    // 2. Authenticate using AuthenticationManager
	    Authentication verifiedToken = authMgr.authenticate(token);

	    // 3. Authentication and authorization successful, generate JWT token
	    String jwt = jwtUtils.generateJwtToken(verifiedToken);
	    
	    // 4. Get user role and user ID from the authenticated user
	    CustomUserDetails userDetails = (CustomUserDetails) verifiedToken.getPrincipal();
	    
	    Long userId = userDetails.getId();  // Fetch the user ID
	    Role role = Role.valueOf(userDetails.getAuthorities().stream()
	                    .findFirst().get().getAuthority());

	    // 5. Create a response object with JWT, role, and userId
	    SigninResponse resp = new SigninResponse(jwt, role, userId, "Successful Auth!!!!");
	    
	    // 6. Return the response
	    return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}



	@PostMapping(value="/user/{userId}/image_upload",consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImageToServerSideFolder(@PathVariable Long userId,
			@RequestParam MultipartFile imageFile) throws IOException{
		System.out.println("in upload img " + userId + " " + imageFile.getOriginalFilename());
		return new ResponseEntity<>(userService.uploadImage(userId, imageFile), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/user/{userId}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE  })
	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Long userId) throws IOException {
		System.out.println("in serve img " + userId);
		return new ResponseEntity<>(userService.serveImage(userId), HttpStatus.OK);
	}
	
	@PostMapping("user/logout")
    public ResponseEntity<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
        	userService.logout(authentication);
            return new ResponseEntity<>("Logout successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No user is currently logged in", HttpStatus.BAD_REQUEST);
        }
    }
	
	
	@PostMapping("user/forgotPassword")
	public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPassword resetPasswordDto) {
	    // Fetch user by email
	    Login user = userService.fetchByEmail(resetPasswordDto.getEmail());
  
	    // If user exists, update the password
	    if (user != null) {
	        // Encode the new password before saving it
	        user.setPassword(encoder.encode(resetPasswordDto.getNewPassword()));
	        userService.updateUser(user); // Method to save the updated user in the database

	        return ResponseEntity.ok("Password has been successfully reset.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No account found with that email.");
	    }
	}


}
