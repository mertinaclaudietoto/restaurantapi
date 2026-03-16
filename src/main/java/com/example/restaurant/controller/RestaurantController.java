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
import com.example.restaurant.dto.RestaurantDTO;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService _service;

    @GetMapping
    public List<Restaurant> getUsers() {
        return _service.getAll();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Restaurant>> createUser(@Valid @RequestBody RestaurantDTO  user) {
      
        Restaurant updated =_service.save(user); // peut lancer NotFoundException
        ApiResponse<Restaurant> response = new ApiResponse<>(200, "Restaurant add successfully", updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Restaurant getUser(@PathVariable Long id) {
        return _service.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        _service.delete(id);
    }

}