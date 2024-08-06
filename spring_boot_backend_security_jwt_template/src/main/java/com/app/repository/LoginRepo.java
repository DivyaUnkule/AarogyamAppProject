package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Login;
@Repository
public interface LoginRepo extends JpaRepository<Login, Long> {
	@Query("select u from Login u join fetch u.userRoles where u.email=?1")
	Optional<Login> findByEmail(String email);

}
