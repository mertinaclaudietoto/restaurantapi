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
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
        role.setName(roleDTO.getName());
        Role value = roleRepository.save(role);
        return buildRoleDTO(value);

    }
    public RoleDTO buildRoleDTO(Role value){
        RoleDTO response = new RoleDTO();
        response.setName(value.getName());
        response.setId(value.getId());
        return response;    
    } 
    public RoleDTO get(Long id) {
        Role value = roleRepository.findById(id).orElse(null);
        return buildRoleDTO(value);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

}