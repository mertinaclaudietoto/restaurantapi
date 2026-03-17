package com.example.restaurant.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
     @Query("""
        SELECT r, AVG(a.note) as moyenne,r.nom
        FROM Restaurant r
        LEFT JOIN Avis a ON a.restaurantId = r.id
        GROUP BY r.id,r.nom
        ORDER BY moyenne DESC
        """)
    List<Object[]> findTopRestaurants(Pageable pageable);

    @Query("""
        SELECT DISTINCT r FROM Restaurant r
        LEFT JOIN Avis a ON a.restaurantId = r.id
        WHERE (:ville IS NULL OR LOWER(r.ville) LIKE LOWER(CONCAT('%', :ville, '%')))
        AND (:nom IS NULL OR LOWER(r.nom) LIKE LOWER(CONCAT('%', :nom, '%')))
        GROUP BY r.id
        HAVING (:noteMin IS NULL OR AVG(a.note) >= :noteMin)
    """)
    Page<Restaurant> searchRestaurants(
            @Param("ville") String ville,
            @Param("nom") String nom,
            @Param("noteMin") Double noteMin,
            Pageable pageable
    );
}