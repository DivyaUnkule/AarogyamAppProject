package com.app.dto;

import com.app.entities.Login;
import com.app.enums.Role;

public class SigninResponse {
	private String jwt;
	private String msg;
	private Role role;
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
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
    
	public SigninResponse(String jwt, Role role,String msg) {
		super();
		this.jwt = jwt;
		this.role = role;
		this.msg = msg;
		
	}
	public SigninResponse() {
		super();
	}
	@Override
	public String toString() {
		return "SigninResponse [jwt=" + jwt + ", msg=" + msg + ", role=" + role + "]";
	}
	
	
	
	
	
	
	
	
	
}
