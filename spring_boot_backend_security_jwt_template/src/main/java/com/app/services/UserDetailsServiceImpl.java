package com.app.services;

import com.app.dto.UserDetailsDTO;
import com.app.entities.Login;
import com.app.entities.RoleEntity;
import com.app.enums.Role;
import com.app.repositories.RoleEntityRepo;
import com.app.repositories.UserDetailsRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
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
    
    @Autowired
    private RoleEntityRepo rolerepo;

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
        return userRepo.findAll().stream()
                .map(user -> {
                    UserDetailsDTO dto = new UserDetailsDTO();
                    dto.setUserId(user.getId());
                    dto.setEmail(user.getEmail());
                    dto.setPassword(user.getPassword()); 
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setPhoneNo(user.getPhoneNo());
                    dto.setStatus(user.getStatus());
                    dto.setAddress(user.getAddress());
                    dto.setGender(user.getGender());
                    dto.setRoles(user.getUserRoles()); 
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDTO getUserById(Long id) {
        Optional<Login> optionalUser = userRepo.findById(id); 
        if (optionalUser.isPresent()) {
            Login user = optionalUser.get();
            UserDetailsDTO dto = new UserDetailsDTO();
            dto.setUserId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword()); 
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setPhoneNo(user.getPhoneNo());
            dto.setStatus(user.getStatus());
            dto.setAddress(user.getAddress());
            dto.setGender(user.getGender());
            dto.setRoles(user.getUserRoles()); 
            return dto;
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
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

            if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            	/*Login roleEntity = rolerepo.findByRoleName(userDTO.getRoles().iterator().next().getRoleName())
                        .orElseThrow(() -> new RuntimeException("Role not found"));*/
                user.setUserRoles(userDTO.getRoles());
            }

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
