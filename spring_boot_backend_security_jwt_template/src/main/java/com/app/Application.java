package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.entities.RoleEntity;
import com.app.enums.Role;
import com.app.repositories.RoleEntityRepo;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.Conditions;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Value("${file.profile.upload.location}")
	private String profilePictureFolderPath;

	@Autowired
	private RoleEntityRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean // equivalent to <bean id ..../> in xml file
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		File dir = new File(profilePictureFolderPath);
		if (!dir.exists())
			dir.mkdirs();

		
		/*
		 * RoleEntity role1 = new RoleEntity(); role1.setRoleName(Role.ROLE_ADMIN);
		 * RoleEntity role2 = new RoleEntity();
		 * role2.setRoleName(Role.ROLE_REGULARUSER); RoleEntity role3 = new
		 * RoleEntity(); role3.setRoleName(Role.ROLE_WEIGHTGAINUSER); RoleEntity role4 =
		 * new RoleEntity(); role4.setRoleName(Role.ROLE_WEIGHTLOSSUSER);
		 * Set<RoleEntity> roles = new HashSet<RoleEntity>(); roles.add(role1);
		 * roles.add(role2); for(RoleEntity role : roles) { Optional<Role> existingUser
		 * = roleRepo.findByRoleName(role.getRoleName());
		 * 
		 * if (existingUser.isEmpty()) { // If the user doesn't exist, insert them
		 * roleRepo.save(role); } }
		 */
		 
	}

}
