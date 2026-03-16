package com.example.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.RestaurantDTO;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant save(RestaurantDTO RestaurantDTO) {
        Restaurant Restaurant = new Restaurant();
        Restaurant.setNom(RestaurantDTO.getNom());
        Restaurant.setAdresse(RestaurantDTO.getAdresse());
        Restaurant.setVille(RestaurantDTO.getVille());
        Restaurant.setDescription(RestaurantDTO.getDescription());
        return restaurantRepository.save(Restaurant);
    }

    @Transactional
    public Restaurant update(Long id, RestaurantDTO RestaurantDTO) {
        Restaurant Restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id: " + id));
        Restaurant.setNom(RestaurantDTO.getNom());
        Restaurant.setAdresse(RestaurantDTO.getAdresse());
        Restaurant.setVille(RestaurantDTO.getVille());
        Restaurant.setDescription(RestaurantDTO.getDescription());
        return restaurantRepository.save(Restaurant);
    }
    public Restaurant get(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

}