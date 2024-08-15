package com.app.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception_handler.ResourceNotFoundException;
import com.app.dto.ApiResponse;

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
		
		
		

	public UserRegResponse userRegistration(Signup reqDTO) throws IOException {
		//dto --> entity
		Login userEntity = mapper.map(reqDTO, Login.class);
		// 2. Map Set<UserRole : enum> ---> Set<Role :entity> n assign it to the
		// transient user entity
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(reqDTO.getRoles()));
		// 3. encode pwd
		userEntity.setPassword(encoder.encode(reqDTO.getPassword()));
		
	
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(reqDTO.getRoles()));
		userEntity.setPassword(encoder.encode(reqDTO.getPassword()));
		

		// 4 : Save user details
		Login persistentUser = loginRepo.save(userEntity);
		
		
		
		return new UserRegResponse("User registered successfully with ID " + persistentUser.getId());
	}

	@Override
	public ApiResponse uploadImage(Long userId, MultipartFile imageFile) throws IOException, ResourceNotFoundException{
		Login user = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid client Id : Image Uploading failed!!!!!!!!"));
		String targetPath = profilePictureFolderPath + File.separator + imageFile.getOriginalFilename();

		System.out.println(targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		user.setProfilePicPath(targetPath);
		return new ApiResponse("Image Uploaded successfully!");
	}
	
	@Override
	public byte[] serveImage(Long userId) throws IOException {
		Login user = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid client Id : Image Download failed!!!!!!!!"));
		String path = user.getProfilePicPath();
		if (path == null)
			throw new ResourceNotFoundException("Image does not exist !!!!!");
		return Files.readAllBytes(Paths.get(path));

	}
	
	 @Override
	    public void logout(Authentication authentication) {
	        
	        SecurityContextHolder.clearContext();
	    }
	 
	 

	     @Override
	     public Login fetchByEmail(String email) {
	         // Find user by email using the repository method
	         return userDao.findByEmailIgnoreCase(email);
	     }
	 

	 
	 @Override
	 public void updateUser(Login user) {
		 userDao.save(user);
		}

	 
	 
	
	

}
