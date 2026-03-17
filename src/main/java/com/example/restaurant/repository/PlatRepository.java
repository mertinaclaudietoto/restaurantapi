package com.example.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurant.model.Plat;

public interface PlatRepository extends JpaRepository<Plat, Long> {
    Page<Plat> findByRestaurantId(Long restaurantId, Pageable pageable);
}