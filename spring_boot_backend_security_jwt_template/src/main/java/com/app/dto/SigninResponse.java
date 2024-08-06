package com.app.dto;

import java.time.LocalDateTime;

public class SigninResponse {
	private String jwt;
	private String message;
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public SigninResponse(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
		
	}
	public SigninResponse() {
		super();
	}
	
	
}
