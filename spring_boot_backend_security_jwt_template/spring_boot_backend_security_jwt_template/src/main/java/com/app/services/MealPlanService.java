package com.app.services;

import java.util.List;

import com.app.dto.MealPlanDTO;
import com.app.entities.MealPlan;

public interface MealPlanService {

	MealPlan createMealPlan(MealPlanDTO mealPlanDTO);
	MealPlan updateMealPlan(MealPlanDTO mealplandto);
	 void deleteMealPlan(Long mealPlanId);
	 List<MealPlan> getAllMealPlans();
	List < MealPlan> getMealPlansForUser(Long userId);
	List< MealPlan>  getMealPlansForWeightLossUser(Long userId);
	 List <MealPlan > getMealPlansForWeightGainUser(Long userId);
	 MealPlan getMealPlanById(Long mealId);
}
