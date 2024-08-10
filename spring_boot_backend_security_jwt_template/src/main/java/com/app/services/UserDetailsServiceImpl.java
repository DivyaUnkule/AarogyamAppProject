package com.app.services;

import com.app.dto.UserDetailsDTO;
import com.app.entities.Login;
import com.app.repositories.UserDetailsRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Login> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<Login> getUserById(Long id) {
        return userRepo.findById(id);
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
