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
import com.example.restaurant.dto.FavoriDTO;
import com.example.restaurant.model.Favori;
import com.example.restaurant.service.FavoriService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/favoris")
@RequiredArgsConstructor
public class FavoriController {

    private final FavoriService _service;

    @GetMapping
    public List<Favori> getUsers() {
        return _service.getAll();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Favori>> createUser(@Valid @RequestBody FavoriDTO  user) {
      
        Favori updated =_service.save(user); // peut lancer NotFoundException
        ApiResponse<Favori> response = new ApiResponse<>(200, "Favori add successfully", updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Favori getUser(@PathVariable Long id) {
        return _service.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        _service.delete(id);
    }

}