package com.example.restaurant.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    // @GetMapping
    // public List<Plat> getUsers() {
    //     return _service.getAll();
    // }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Plat>> createUser(@PathVariable Long id,@Valid @RequestBody PlatDTO  user) {
        Plat updated =_service.update(id,user);
        ApiResponse<Plat> response = new ApiResponse<>(200, "Plat update successfully", updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Plat getUser(@PathVariable Long id) {
        return _service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Long id) {
        _service.delete(id);
        ApiResponse<Boolean> response = new ApiResponse<>(200, String.format("Plat %d deleted successfully", id), true);
        return ResponseEntity.ok(response);
    }

}