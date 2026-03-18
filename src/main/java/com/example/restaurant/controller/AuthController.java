package com.example.restaurant.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.ApiResponse;
import com.example.restaurant.dto.LoginDTO;
import com.example.restaurant.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService service;

    // 🔹 login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token= service.login(loginDTO.getEmail(), loginDTO.getPassword());
        ApiResponse<String> response = new ApiResponse<>(200, "login  successfully", token);
        return ResponseEntity.ok(response);
    }

    
    
    @PostMapping("/logout")
    public String logout() {
        return "Logout réussi";
    }
}