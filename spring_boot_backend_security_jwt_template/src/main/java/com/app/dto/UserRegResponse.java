package com.app.dto;

import java.time.LocalDateTime;

public class UserRegResponse {
	
	private String message;
	private LocalDateTime timeStamp;
	public UserRegResponse(String message) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}


}
