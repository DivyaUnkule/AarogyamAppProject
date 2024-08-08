package com.app.repositories;

import com.app.entities.ExerciseYoga;
import com.app.enums.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseYogaRepository extends JpaRepository<ExerciseYoga, Long> {
	
   List<ExerciseYoga> findByRoleRoleName(Role roleName);
    
}
