package com.example.restaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Users;
import com.example.restaurant.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.restaurant.exception.NotFoundException;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    public String login(String email, String password) {
        Optional<Users> userOptional = repo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Email  not found");
        }
        Users user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        // 🎟 génération du token JWT
        return jwtService.generateToken(
                user.getId(),
                user.getIdrole(),
                user.getEmail()
        );
    }

}