package com.example.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.FavoriDTO;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Plat;
import com.example.restaurant.model.Favori;
import com.example.restaurant.repository.FavoriRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriService {

    private final FavoriRepository favoriRepository;
    

    public List<Favori> getAll() {
        return favoriRepository.findAll();
    }

    public Favori save(FavoriDTO dto) {
        Favori value = new Favori();
        value.setUserId(dto.getUserId());
        value.setRestaurantId(dto.getRestaurantId());
        return favoriRepository.save(value);
    }

    // @Transactional
    // public Favori update(Long id, FavoriDTO dto) {
    //     Favori value = favoriRepository.findById(id)
    //             .orElseThrow(() -> new NotFoundException("Favori not found with id: " + id));
    //     value.setUserId(dto.getUserId());
    //     value.setRestaurantId(dto.getRestaurantId());
    //     return favoriRepository.save(value);
    // }
    public Favori get(Long id) {
        return favoriRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        favoriRepository.deleteById(id);
    }

}