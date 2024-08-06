package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

import org.modelmapper.Conditions;

@SpringBootApplication
public class Application implements CommandLineRunner  {
	@Value("${file.profile.upload.location}")
	private String profilePictureFolderPath;

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
	}
}
