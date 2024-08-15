package com.app.services;

import java.io.IOException;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;

import com.app.dto.Signup;
import com.app.dto.UserRegResponse;
import com.app.entities.Login;



public interface IUserService {

	UserRegResponse userRegistration(Signup user) throws IOException;

	void updateUser(Login user);
	
	ApiResponse uploadImage(Long userId, MultipartFile imageFile) throws IOException;
	
	byte[] serveImage(Long clientId) throws IOException;
	
	void logout(Authentication authentication);
	
	Login fetchByEmail(String email);
	
	
	

}
