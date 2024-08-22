package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.MealPlan;
import com.app.enums.Role;
@Repository
public interface MealPlanRepo extends JpaRepository<MealPlan, Long> {
	
	
	
	@Query("SELECT m FROM MealPlan m WHERE m.role.id = :roleId")
	List<MealPlan> findByRoleId(@Param("roleId") Long roleId);
	
	
}


