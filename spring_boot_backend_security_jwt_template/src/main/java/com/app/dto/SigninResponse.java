package com.app.dto;

import com.app.entities.Login;

public class SigninResponse {
	private String jwt;
	private String msg;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public SigninResponse(String jwt, String msg) {
		super();
		this.jwt = jwt;
		this.msg = msg;
	}
	public SigninResponse() {
		super();
	}
	@Override
	public String toString() {
		return "SigninResponse [jwt=" + jwt + ", msg=" + msg + "]";
	}
	
	
	
	
	
	
}
