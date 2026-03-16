package com.example.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}