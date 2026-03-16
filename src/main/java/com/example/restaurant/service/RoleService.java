package com.example.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.RoleDTO;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Role;
import com.example.restaurant.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Role save(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return roleRepository.save(role);
    }

    @Transactional
    public Role update(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
        role.setName(roleDTO.getName());
        return roleRepository.save(role);
    }
    public Role get(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

}