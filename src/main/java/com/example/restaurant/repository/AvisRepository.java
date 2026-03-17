package com.example.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.restaurant.model.Avis;
public interface AvisRepository extends JpaRepository<Avis, Long> {
    Page<Avis> findByRestaurantId(Long restaurantId, Pageable pageable);

    @Query("SELECT AVG(a.note) FROM Avis a WHERE a.restaurantId = :restaurantId")
    Double findAverageNoteByRestaurantId(@Param("restaurantId") Long restaurantId);

     @Query("SELECT COUNT(a) FROM Avis a WHERE a.restaurantId = :restaurantId")
    Long countByRestaurantId(@Param("restaurantId") Long restaurantId);
}