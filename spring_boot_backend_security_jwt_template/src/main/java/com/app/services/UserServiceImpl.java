package com.app.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Clock;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.Signup;
import com.app.dto.UserRegResponse;
import com.app.entities.Login;
import com.app.repositories.LoginRepo;
import com.app.repositories.RoleEntityRepo;
import com.app.security.CustomUserDetails;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
	//dep : dao layer i/f
		@Autowired
		private LoginRepo userDao;
		//dep
		@Autowired
		private ModelMapper mapper;
		//dep 
		@Autowired
		private PasswordEncoder encoder;
		@Autowired
		private RoleEntityRepo roleRepo;
		@Autowired
		private LoginRepo loginRepo;
		@Value("${file.profile.upload.location}")
		private String profilePictureFolderPath;
		
		
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Login user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID !!"));
			return new CustomUserDetails(user);
		}

	public UserRegResponse userRegistration(Signup reqDTO) throws IOException {
		//dto --> entity
		Login userEntity = mapper.map(reqDTO, Login.class);
		// 2. Map Set<UserRole : enum> ---> Set<Role :entity> n assign it to the
		// transient user entity
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(reqDTO.getRoles()));
		// 3. encode pwd
		userEntity.setPassword(encoder.encode(reqDTO.getPassword()));
		
	Clock clock = Clock.systemDefaultZone();
	long milliSeconds = clock.millis();
         MultipartFile profilePictureFile = reqDTO.getProfilePicPath();
         String completePath = profilePictureFolderPath + File.separator + milliSeconds
				+ profilePictureFile.getOriginalFilename();
			Files.copy(profilePictureFile.getInputStream(), Paths.get(completePath),
					StandardCopyOption.REPLACE_EXISTING);

		userEntity.setProfilePicPath(completePath);
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(reqDTO.getRoles()));
		userEntity.setPassword(encoder.encode(reqDTO.getPassword()));
		

		// 4 : Save user details
		Login persistentUser = loginRepo.save(userEntity);
		
		
		
		return new UserRegResponse("User registered successfully with ID " + persistentUser.getId());
	}

	
	

}
