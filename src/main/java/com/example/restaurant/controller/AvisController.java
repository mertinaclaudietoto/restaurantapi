package com.example.restaurant.controller; 

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.service.AvisService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
public class AvisController {

    
    private final AvisService _service;
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        _service.delete(id);
    }


}