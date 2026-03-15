package com.example.restaurant.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.restaurant.model.Role;
import com.example.restaurant.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Role saveUser(Role user) {
        return roleRepository.save(user);
    }

    public Role getUser(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        roleRepository.deleteById(id);
    }

}