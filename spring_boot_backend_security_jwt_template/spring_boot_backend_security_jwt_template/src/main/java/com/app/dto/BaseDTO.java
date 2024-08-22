package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


public class BaseDTO {
	//used during ser.
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseDTO(Long id) {
		super();
		this.id = id;
	}

	public BaseDTO() {
		super();
	}
	

}
