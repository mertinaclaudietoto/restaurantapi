package com.example.restaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.model.Users;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtService jwtService;

    public String login(String email, String password) {
        Optional<Users> user = repo.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            return null;
        }
        return jwtService.generateToken(user.get().getEmail());
    }
}