package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.app.entities.RoleEntity;
import com.app.enums.Gender;
import com.app.enums.Role;
import com.app.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Signup {
	@JsonProperty(access = Access.READ_ONLY) // user id will be serialized n sent to clnt BUT it won't be read from clnt
												// n de-serialized
	private Long userId;
	@Email(message = "Invalid Email!!!")
	@Column(unique = true)
	private String email;
	@Column(length = 350,nullable = false)
	private String password;
    @Column
	private String firstName;
	@Column
	private String lastName;
	/*@Column(nullable = false)
	private MultipartFile profilePicPath;*/
	@Column(length = 14)
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Status status;
	@Column(length = 100, nullable = false)
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Gender gender;
   // many-to-many , User *--->* Role
	@NotEmpty(message = "at least 1 role should be chosen")
	private Set<Role> roles = new HashSet<>();
	//private String adminSecretKey;
	public Signup(Long userId, String email, String password, String firstName, String lastName, MultipartFile profilePicPath,
			String phoneNo, Status status, String address, Gender gender,
			@NotEmpty(message = "at least 1 role should be chosen") Set<Role> roles) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.profilePicPath = profilePicPath;
		this.phoneNo = phoneNo;
		this.status = status;
		this.address = address;
		this.gender = gender;
		this.roles = roles;
	}
	public Signup() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	/*public MultipartFile getProfilePicPath() {
		return profilePicPath;
	}
	public void setProfilePicPath(MultipartFile profilePicPath) {
		this.profilePicPath = profilePicPath;
	}*/
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Signup [userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNo=" + phoneNo + ", status="
				+ status + ", address=" + address + ", gender=" + gender + ", roles=" + roles + "]";
	}
	
	/*public String getAdminSecretKey() {
		return adminSecretKey;
	}
	public void setAdminSecretKey(String adminSecretKey) {
		this.adminSecretKey = adminSecretKey;
	}*/
	
	

}

