package com.example.restaurant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.AvisDTO;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Avis;
import com.example.restaurant.repository.AvisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvisService {

    private final AvisRepository AvisRepository;
    

    public Page<Avis> getAll(Long restaurantId, Pageable pageable) {
    if (restaurantId != null) {
        return AvisRepository.findByRestaurantId(restaurantId, pageable);
    } else {
        return AvisRepository.findAll(pageable);
    }
}

    public Avis save(Long id,AvisDTO dto) {
        Avis avis = new Avis();
        avis.setNote(dto.getNote());
        avis.setCommentaire(dto.getCommentaire());
        avis.setUserId(dto.getUserId());
        avis.setRestaurantId(id);
        return AvisRepository.save(avis);
    }
    @Transactional
    public Avis update(Long id, AvisDTO dto) {
        Avis avis = AvisRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id: " + id));
            avis.setNote(dto.getNote());
            avis.setCommentaire(dto.getCommentaire());
            avis.setUserId(dto.getUserId());
            avis.setRestaurantId(dto.getRestaurantId());
        return AvisRepository.save(avis);
    }
    public Avis get(Long id) {
        return AvisRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        AvisRepository.deleteById(id);
    }

}