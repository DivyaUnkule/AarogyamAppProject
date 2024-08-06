package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;//need to ask
import lombok.Setter;

import com.app.enums.Role;



@Entity
@Table(name = "roles")
//@RequiredArgsConstructor(staticName = "roleName")//need to ask...how to implement the java code for this
public class RoleEntity extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(length = 20,unique = true)
	private Role roleName;

	public Role getRoleName() {
		return roleName;
	}

	public void setRoleName(Role roleName) {
		this.roleName = roleName;
	}

	public RoleEntity(Role roleName) {
		super();
		this.roleName = roleName;
	}

	public RoleEntity() {
		super();
	}

	
	
	
	

	
}

