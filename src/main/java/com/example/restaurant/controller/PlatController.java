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
import com.example.restaurant.dto.PlatDTO;
import com.example.restaurant.model.Plat;
import com.example.restaurant.service.PlatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/plats")
@RequiredArgsConstructor
public class PlatController {

    private final PlatService _service;

    @GetMapping
    public List<Plat> getUsers() {
        return _service.getAll();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Plat>> createUser(@Valid @RequestBody PlatDTO  user) {
      
        Plat updated =_service.save(user); // peut lancer NotFoundException
        ApiResponse<Plat> response = new ApiResponse<>(200, "Plat add successfully", updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Plat getUser(@PathVariable Long id) {
        return _service.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        _service.delete(id);
    }

}