package com.example.restaurant.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.ApiResponse;
import com.example.restaurant.dto.RoleDTO;
import com.example.restaurant.model.Role;
import com.example.restaurant.service.RoleService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Role> getRole() {
        return roleService.getAllRole();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public Role createUser(@RequestBody RoleDTO user) {
        return roleService.save(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> updateUser(@PathVariable Long id, @RequestBody RoleDTO user) {
        RoleDTO updatedRole = roleService.update(id, user); // peut lancer NotFoundException
        ApiResponse<RoleDTO> response = new ApiResponse<>(200, "Role updated successfully", updatedRole);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> getUser(@PathVariable Long id) {
        RoleDTO updatedRole =roleService.get(id);
        ApiResponse<RoleDTO> response = new ApiResponse<>(200, "Role get successfully", updatedRole);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Long id) {
        roleService.delete(id);
        ApiResponse<Boolean> response = new ApiResponse<>(200, String.format("Role %d deleted successfully", id), true);
        return ResponseEntity.ok(response);
    }

}