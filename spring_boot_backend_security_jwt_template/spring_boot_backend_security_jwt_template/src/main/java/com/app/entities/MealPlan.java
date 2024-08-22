package com.app.entities;

import javax.persistence.*;

import com.app.enums.Status;


@Entity
@Table(name = "meal_plans")
public class MealPlan extends BaseEntity{
	
	@Column(length = 255, nullable = false)
	private String name;

	@Column(nullable = false , columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private double amount;


	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	// @JsonProperty("role")
	private RoleEntity role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public MealPlan( String name, String description, double amount, Status status, RoleEntity role) {
		
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.role = role;
	}

	@Override
	public String toString() {
		return "MealPlan [name=" + name + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ "]";
	}

	public MealPlan() {
		super();
	}
	
	
	
}
