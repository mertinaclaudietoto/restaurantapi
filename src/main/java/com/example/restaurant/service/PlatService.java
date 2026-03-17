package com.example.restaurant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.PlatDTO;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Plat;
import com.example.restaurant.repository.PlatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlatService {

    private final PlatRepository platRepository;
    

    public Page<Plat> getAll(Long restaurantId, Pageable pageable) {
    if (restaurantId != null) {
        return platRepository.findByRestaurantId(restaurantId, pageable);
    } else {
        return platRepository.findAll(pageable);
    }
}

    public Plat save(Long idRestaurant ,PlatDTO dto) {
        Plat plat = new Plat();
        plat.setNom(dto.getNom());
        plat.setPrix(dto.getPrix());
        plat.setRestaurantId(idRestaurant);
        return platRepository.save(plat);
    }

    @Transactional
    public Plat update(Long id, PlatDTO dto) {
        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plat not found with id: " + id));
        plat.setNom(dto.getNom());
        plat.setPrix(dto.getPrix());
        return platRepository.save(plat);
    }
    public Plat get(Long id) {
        return platRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        platRepository.deleteById(id);
    }

}