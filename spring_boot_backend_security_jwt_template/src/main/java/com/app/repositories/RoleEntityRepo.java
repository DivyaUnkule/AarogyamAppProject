package com.app.repositories;



import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Login;
import com.app.entities.RoleEntity;
import com.app.enums.Role;


@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Long> {
	
	Set<RoleEntity> findByRoleNameIn(Set<Role> roles);

	Optional<Login> findByRoleName(Role roleName);


}
