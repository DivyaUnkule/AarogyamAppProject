package com.app.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.Signup;
import com.app.dto.UserRegResponse;

import io.swagger.v3.oas.annotations.servers.Server;

public interface IUserService {

	UserRegResponse userRegistration(Signup user) throws IOException;

	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
	
	
	

}
