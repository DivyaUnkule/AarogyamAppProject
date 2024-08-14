package com.app.services;

import java.io.IOException;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;

import com.app.dto.Signup;
import com.app.dto.UserRegResponse;



public interface IUserService {

	UserRegResponse userRegistration(Signup user) throws IOException;

	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
	
	ApiResponse uploadImage(Long userId, MultipartFile imageFile) throws IOException;
	
	byte[] serveImage(Long clientId) throws IOException;
	
	void logout(Authentication authentication);
	
	
	

}
