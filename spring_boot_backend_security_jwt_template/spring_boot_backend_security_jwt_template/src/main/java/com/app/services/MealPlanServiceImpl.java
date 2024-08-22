package com.app.services;
import java.util.List;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.app.dto.MealPlanDTO;
import com.app.entities.MealPlan;
import com.app.entities.RoleEntity;
import com.app.repositories.MealPlanRepo;
import com.app.repositories.RoleEntityRepo;


@Service
@Transactional
public class MealPlanServiceImpl implements MealPlanService {

	@Autowired
	private MealPlanRepo mealPlanRepository;

	@Autowired
	private RoleEntityRepo roleRepository;

	public MealPlan createMealPlan(MealPlanDTO mealPlanDTO) {
		// Convert MealPlanDTO to MealPlan
		MealPlan mealPlan = new MealPlan();
		mealPlan.setName(mealPlanDTO.getName());
		mealPlan.setDescription(mealPlanDTO.getDescription());
		mealPlan.setAmount(mealPlanDTO.getAmount());
		mealPlan.setStatus(mealPlanDTO.getStatus());

		// Fetch RoleEntity from the database
		RoleEntity role = roleRepository.findById(mealPlanDTO.getRoleid())
				.orElseThrow(() -> new IllegalArgumentException("Role not found."));

		// Set RoleEntity in MealPlan
		mealPlan.setRole(role);

		// Save MealPlan
		return mealPlanRepository.save(mealPlan);
	}

	@Override
	public MealPlan updateMealPlan(MealPlanDTO mealPlanDTO) {

		// Fetch the existing MealPlan from the database
		MealPlan existingMealPlan = mealPlanRepository.findById(mealPlanDTO.getMealid())
				.orElseThrow(() -> new IllegalArgumentException("Meal Plan not found."));

		// Update the fields of the existing MealPlan
		existingMealPlan.setName(mealPlanDTO.getName());
		existingMealPlan.setDescription(mealPlanDTO.getDescription());
		existingMealPlan.setAmount(mealPlanDTO.getAmount());
		existingMealPlan.setStatus(mealPlanDTO.getStatus());

		// Save the updated MealPlan
		return mealPlanRepository.save(existingMealPlan);
	}

	@Override
	public void deleteMealPlan(Long mealPlanId) {
		MealPlan existingMealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new IllegalArgumentException("Meal Plan not found."));

        // Delete the MealPlan
        mealPlanRepository.delete(existingMealPlan);
    }
	
	@Override
	public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }
	@Override
    public List<MealPlan> getMealPlansForUser(Long roleId) {
        return mealPlanRepository.findByRoleId(roleId);
    }
	@Override
    public List<MealPlan>  getMealPlansForWeightLossUser(Long roleId ) {
        return mealPlanRepository.findByRoleId(roleId);
    }
	@Override
    public  List<MealPlan>  getMealPlansForWeightGainUser(Long roleId) {
        return mealPlanRepository.findByRoleId(roleId);
		
	}

	@Override
	public MealPlan getMealPlanById(Long mealId) {
		
		return mealPlanRepository.findById(mealId).orElseThrow(()-> new IllegalArgumentException("Meal Id not found."));
	} 

}