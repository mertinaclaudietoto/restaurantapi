package com.example.restaurant.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.ApiResponse;
import com.example.restaurant.dto.UserDTO;
import com.example.restaurant.dto.UserResponseDTO;

import com.example.restaurant.model.Users;
import com.example.restaurant.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //ajout pagination
   @GetMapping
    public List<UserResponseDTO> getUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Users>> createUser(@Valid @RequestBody UserDTO user) {
      
        Users updatedRole =userService.saveUser(user); // peut lancer NotFoundException
        ApiResponse<Users> response = new ApiResponse<>(200, "User add successfully", updatedRole);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable Long id) {
        UserResponseDTO value= userService.getUser(id);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(200, "User deleted successfully", value);
        return ResponseEntity.ok(value);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>>  deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        // peut lancer NotFoundException
        ApiResponse<Boolean> response = new ApiResponse<>(200, "User deleted successfully", true);
        return ResponseEntity.ok(response);
        
    }

}