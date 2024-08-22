package com.app.services;

import com.app.custom_exception_handler.ResourceNotFoundException;
import com.app.dto.UserDetailsDTO;
import com.app.entities.Login;
import com.app.entities.RoleEntity;
import com.app.repositories.UserDetailsRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

    @Autowired
    private UserDetailsRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Login createUser(UserDetailsDTO userDTO) {
        Login user = new Login();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNo(userDTO.getPhoneNo());
        user.setStatus(userDTO.getStatus());
        user.setAddress(userDTO.getAddress());
        user.setGender(userDTO.getGender());
        // Assuming roles are stored as RoleEntity
        user.setUserRoles(userDTO.getRoles()); 
        return userRepo.save(user);
    }

    @Override
    public List<UserDetailsDTO> getAllUsers() {
    	 List<Login> users = userRepo.findAll();
    	    return users.stream()
    	                .map(this::convertToUserDetailsDTO)
    	                .collect(Collectors.toList());
    }
    private UserDetailsDTO convertToUserDetailsDTO(Login login) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUserId(login.getId());
        dto.setEmail(login.getEmail());
        dto.setFirstName(login.getFirstName());
        dto.setLastName(login.getLastName());
        dto.setPhoneNo(login.getPhoneNo());
        dto.setStatus(login.getStatus());
        dto.setAddress(login.getAddress());
        dto.setGender(login.getGender());
        dto.setRoles(login.getUserRoles());
        // Don't set the password in the DTO for security reasons
        return dto;
    }
    
    @Override
    public UserDetailsDTO getUserById(Long userId) {
        Login login = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return convertToUserDetailsDTO(login);
    }
    

    
    @Override
    public Login updateUser(UserDetailsDTO userDTO) {
        Optional<Login> userOpt = userRepo.findById(userDTO.getUserId());

        if (userOpt.isPresent()) {
            Login user = userOpt.get();
         
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNo(userDTO.getPhoneNo());
            user.setStatus(userDTO.getStatus());
            user.setAddress(userDTO.getAddress());
            user.setGender(userDTO.getGender());
            user.setUserRoles(userDTO.getRoles());

            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            return userRepo.save(user);
        }

        throw new RuntimeException("User not found");
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
