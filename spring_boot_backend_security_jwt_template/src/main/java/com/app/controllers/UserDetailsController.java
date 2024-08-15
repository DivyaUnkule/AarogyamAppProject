package com.app.controllers;

import com.app.dto.UserDetailsDTO;
import com.app.entities.Login;
import com.app.services.IUserDetailsService;
import com.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserDetailsController {

    @Autowired
    private IUserDetailsService userService;

    // Admin: Create a new user
    @PostMapping("/admin/addUser")
    public ResponseEntity<Login> createUser(@RequestBody UserDetailsDTO userDTO) {
        Login createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Admin: Get all users info
    @GetMapping("/admin/getAllUsers")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        List<UserDetailsDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Admin: Get user by ID
    
    @GetMapping("/admin/getUserById")
    public ResponseEntity<UserDetailsDTO> getUserById(@RequestParam Long id) {
    	UserDetailsDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

 // Admin: Update user info
    @PutMapping("/admin/updateUserDetails")
    public ResponseEntity<Login> updateUser(@RequestBody UserDetailsDTO userDTO) {
        Login updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Admin: Delete user by ID
    @DeleteMapping("/admin/deleteUserById")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

 // Users: Update their own info
    @PutMapping("/updateOwnDetails")
    public ResponseEntity<Login> updateOwnInfo(@RequestBody UserDetailsDTO userDTO) {
        Login updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }
  // Users: Get user By ID
   @GetMapping("/getUser")
    public ResponseEntity<UserDetailsDTO> getUser(@RequestParam Long id) {
    	UserDetailsDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    
    
    
 
}
