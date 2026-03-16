package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurant.model.Favori;
public interface FavoriRepository extends JpaRepository<Favori, Long> {

}