package com.example.restaurant.service;

import java.util.List;

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
    

    public List<Plat> getAll() {
        return platRepository.findAll();
    }

    public Plat save(PlatDTO dto) {
        Plat plat = new Plat();
        plat.setNom(dto.getNom());
        plat.setPrix(dto.getPrix());
        plat.setRestaurantId(dto.getRestaurantId());
        return platRepository.save(plat);
    }

    @Transactional
    public Plat update(Long id, PlatDTO dto) {
        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id: " + id));
        plat.setNom(dto.getNom());
        plat.setPrix(dto.getPrix());
        plat.setRestaurantId(dto.getRestaurantId());
        return platRepository.save(plat);
    }
    public Plat get(Long id) {
        return platRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        platRepository.deleteById(id);
    }

}