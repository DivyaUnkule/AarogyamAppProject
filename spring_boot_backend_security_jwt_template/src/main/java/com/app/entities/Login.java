package com.app.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.app.enums.Gender;
import com.app.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class Login extends BaseEntity {
	@Email(message = "Invalid Email!!!")
	@Column(unique = true)
	private String email;
	@Column(length = 350,nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore //o skip serializing the userRoles in one direction:used to avoid recursion
	private Set<RoleEntity> userRoles = new HashSet<>();
    @Column
	private String firstName;
	@Column
	private String lastName;
	@Column(length=300)
	private String profilePicPath;
	@Column(length = 14, unique = true)
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Status status;
	@Column(length = 100, nullable = false)
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Gender gender;

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

	public Set<RoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<RoleEntity> userRoles) {
		this.userRoles = userRoles;
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

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

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

	@Override
	public String toString() {
		return "UserEntity [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", profilePicPath=" + profilePicPath + ", phoneNo=" + phoneNo + ", status=" + status
				+ ", address=" + address + ", gender=" + gender + "]";
	}

	

}
