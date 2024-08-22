package com.app.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.MealPlanDTO;
import com.app.entities.MealPlan;
import com.app.repositories.UserRepo;
import com.app.services.MealPlanService;

import com.app.services.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins ="http://localhost:3000" , allowCredentials = "true")
public class MealPlanController{
                      
	@Autowired
	private MealPlanService mealPlanService;
    @Autowired 
    UserRepo userRepo;
	private UserServiceImpl userService;
	 // Service to handle RoleEntity

	// Endpoint to add a new meal plan
	@PostMapping("/admin/insertmealplan")
	public ResponseEntity<MealPlan> insertMealPlan(@RequestBody MealPlanDTO mealDTO) {
		System.out.println("in controller"+mealDTO);
	MealPlan creadtedMealplan =	mealPlanService.createMealPlan(mealDTO);
		return ResponseEntity.status(200).body(creadtedMealplan);
		
	}
	
	@PutMapping("/admin/updatemealplan")
	public ResponseEntity<MealPlan> updateMealPlan(@RequestBody MealPlanDTO mealDTO) {
		System.out.println("in controller"+mealDTO);
		MealPlan updateMealPlan = mealPlanService.updateMealPlan(mealDTO);
		
		return ResponseEntity.status(200).body(updateMealPlan);
		
	}
	
	@DeleteMapping("/admin/deletemealplan/{mealId}")
    public ResponseEntity<Void> deleteMealPlan(@PathVariable Long mealId) {
        mealPlanService.deleteMealPlan(mealId);
        return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/admin/getallmealplan")
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        return ResponseEntity.status(200).body(mealPlans);
        
    }
	
	@GetMapping("/user/regularusermealplan/{userId}")
	    public ResponseEntity<List<MealPlan>> getMealPlansForUser(@PathVariable Long userId) {
		 Long roleId = userRepo.findRoleIdByUserId(userId);
	       List<MealPlan>  mealPlans = mealPlanService.getMealPlansForUser(roleId);
	        return ResponseEntity.status(200).body(mealPlans);
	    }

	// Endpoint for weight loss users to get their meal plans
    @GetMapping("/user/weightlossmealplan/{userId}")
    public ResponseEntity<List<MealPlan>>  getMealPlansForWeightLossUser(@PathVariable Long userId) {
    	Long roleId = userRepo.findRoleIdByUserId(userId);
       List<MealPlan>  mealPlans = mealPlanService.getMealPlansForWeightLossUser(roleId);
        return ResponseEntity.ok(mealPlans);
    }
 // Endpoint for weight gain users to get their meal plans
    @GetMapping("/user/weightgainmealplan/{userId}")
    public ResponseEntity<List<MealPlan>> getMealPlansForWeightGainUser(@PathVariable Long userId) {
    	Long roleId = userRepo.findRoleIdByUserId(userId);
        List<MealPlan>  mealPlans = mealPlanService.getMealPlansForWeightGainUser(roleId);
        return ResponseEntity.ok(mealPlans);
    }
    @GetMapping("/admin/getmealplanId/{mealId}")
    public ResponseEntity<MealPlan> getMealPlansbyId(@PathVariable Long mealId) {
    	  MealPlan  mealPlans = mealPlanService.getMealPlanById(mealId);
        return ResponseEntity.ok(mealPlans);
    }
    
}
