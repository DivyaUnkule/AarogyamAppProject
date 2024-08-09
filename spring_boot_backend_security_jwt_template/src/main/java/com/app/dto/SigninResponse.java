package com.app.dto;

import com.app.entities.Login;

public class SigninResponse {
	private String jwt;
	private Login user;
	
	
	public SigninResponse() {
		super();
	}
	public SigninResponse(String jwt, Login user) {
		super();
		this.jwt = jwt;
		this.user = user;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public Login getUser() {
		return user;
	}
	public void setUser(Login user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "SigninResponse [jwt=" + jwt + ", user=" + user + "]";
	}
	
	
	
}
