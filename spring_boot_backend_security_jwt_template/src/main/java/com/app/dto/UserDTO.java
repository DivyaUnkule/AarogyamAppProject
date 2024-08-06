package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.app.enums.Role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserDTO {
	@JsonProperty(access = Access.READ_ONLY) // user id will be serialized n sent to clnt BUT it won't be read from clnt
												// n de-serialized
	private Long userId;
//	@NotBlank(message = "first name must be supplied")
	private String firstName;
//	@NotBlank(message = "last name must be supplied")
	private String lastName;
	@NotBlank(message = "email must be supplied")
	@Email(message = "Invalid email format")
	private String email;
	@NotBlank(message = "password must be supplied")
	private String password;
	// many-to-many , User *--->* Role
	@NotEmpty(message = "at least 1 role should be chosen")
	private Set<Role> roles = new HashSet<>();
	private String adminSecretKey;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getAdminSecretKey() {
		return adminSecretKey;
	}
	public void setAdminSecretKey(String adminSecretKey) {
		this.adminSecretKey = adminSecretKey;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", adminSecretKey=" + adminSecretKey + "]";
	}
	
	

}

