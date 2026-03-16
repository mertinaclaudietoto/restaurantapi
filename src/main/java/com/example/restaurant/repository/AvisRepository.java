package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurant.model.Avis;
public interface AvisRepository extends JpaRepository<Avis, Long> {

}