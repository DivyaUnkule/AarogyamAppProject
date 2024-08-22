package com.app.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.dto.Signup;
import com.app.dto.UserRegResponse;
import com.app.entities.Login;

import io.swagger.v3.oas.annotations.servers.Server;

public interface IUserService {

	UserRegResponse userRegistration(Signup user) throws IOException;

	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
	
	ApiResponse uploadImage(Long userId, MultipartFile imageFile) throws IOException;

	byte[] serveImage(Long userId) throws IOException;

	void logout(Authentication authentication);

	Login fetchByEmail(String email);

	void updateUser(Login user);
	
	//Login fetchByEmail(String email);
	
	
	

}
