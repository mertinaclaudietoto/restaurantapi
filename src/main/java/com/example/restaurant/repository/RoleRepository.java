package com.example.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}