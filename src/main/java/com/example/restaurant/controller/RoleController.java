package com.example.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.model.Role;
import com.example.restaurant.service.RoleService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> getRole() {
        return roleService.getAllRole();
    }
    @GetMapping("/hello")
        public String hello(){
        return "Hello Spring Boot API";
    }

    @PostMapping
    public Role createUser(@RequestBody Role user) {
        return roleService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Role getUser(@PathVariable Long id) {
        return roleService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        roleService.deleteUser(id);
    }

}