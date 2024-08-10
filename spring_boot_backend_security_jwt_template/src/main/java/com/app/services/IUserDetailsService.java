package com.app.services;

import com.app.dto.UserDetailsDTO;
import com.app.entities.Login;

import java.util.List;
import java.util.Optional;

public interface IUserDetailsService {
    Login createUser(UserDetailsDTO userDTO);
    List<Login> getAllUsers();
    Optional<Login> getUserById(Long id);
    Login updateUser(UserDetailsDTO userDTO);
    void deleteUser(Long id);
   
}
