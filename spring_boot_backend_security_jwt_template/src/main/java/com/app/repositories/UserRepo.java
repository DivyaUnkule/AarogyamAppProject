package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Login;

public interface UserRepo extends JpaRepository<Login, Long> {
	
	
	@Query("select r.id from Login u join u.userRoles r where u.id = :Id")
	Long findRoleIdByUserId(@Param("Id") Long Id);
	
}
