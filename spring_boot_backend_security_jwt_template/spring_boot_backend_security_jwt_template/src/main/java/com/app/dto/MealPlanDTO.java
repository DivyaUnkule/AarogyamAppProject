package com.app.dto;



import com.app.enums.Status;



public class MealPlanDTO {
	
	private Long mealId;

	private String name;

	private String description;

	private double amount;

	private Status status;

	private Long roleid;

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

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public MealPlanDTO(String name, String description, String amount, String status, String roleid,String mealId){
		super();
		this.name = name;
		this.description = description;
		this.amount = Double.parseDouble(amount);
		this.status = Status.valueOf(status);
		this.roleid = Long.parseLong(roleid);
		this.mealId=Long.parseLong(mealId);
		
	}

	public MealPlanDTO() {
		super();
	}

	@Override
	public String toString() {
		return "MealPlanDTO [name=" + name + ", description=" + description + ", amount=" + amount + ", status="
				+ status + "]";
	}

	public Long getMealid() {
		return mealId;
	}

	public void setMealid(Long mealId) {
		this.mealId = mealId;
	}

	
	

}
