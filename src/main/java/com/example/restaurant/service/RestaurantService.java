package com.example.restaurant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.NoteMoyenRestaurantDTO;
import com.example.restaurant.dto.RestaurantDTO;
import com.example.restaurant.dto.TopRestaurantDTO;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.AvisRepository;
import com.example.restaurant.repository.PlatRepository;
import com.example.restaurant.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final PlatRepository platRepository;
    private final AvisRepository avisRepository;

    public Page<Restaurant> searchRestaurants(String ville, String nom, Double noteMin, Pageable pageable) {
        return restaurantRepository.searchRestaurants(ville, nom, noteMin, pageable);
    }

    public void deleteRestaurant(Long restaurantId) {
        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RuntimeException("Restaurant introuvable"));
            restaurantRepository.delete(restaurant);

        } catch (RuntimeException e) {
            // Remonter l'exception
            throw e;
        } catch (Exception e) {
            // Exception générique
            throw new RuntimeException("Impossible de supprimer : le restaurant a des plats rattachés.", e);
        }
    }

   
    public Page<Restaurant> getAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
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

    // public void delete(Long id) {
    //     restaurantRepository.deleteById(id);
    // }
    public Double getAverageNoteByRestaurantId(Long restaurantId) {
        Double avg = avisRepository.findAverageNoteByRestaurantId(restaurantId);
        return avg != null ? avg : 0.0; // Retourne 0 si aucun avis
    }
     public Long getNombreAvisByRestaurantId(Long restaurantId) {
        return avisRepository.countByRestaurantId(restaurantId);
    }
     public NoteMoyenRestaurantDTO getNoteMoyenneEtNombreAvis(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant introuvable"));
        Double noteMoyenne = getAverageNoteByRestaurantId(restaurantId);
        Long nombreAvisLong = getNombreAvisByRestaurantId(restaurantId);
        // Retourner le DTO
        return new NoteMoyenRestaurantDTO(
                restaurant.getId(),
                restaurant.getNom(),
                noteMoyenne,
                nombreAvisLong
        );
    }
   public List<TopRestaurantDTO> getTopRestaurants(Pageable pageable) {
        List<Object[]> results = restaurantRepository.findTopRestaurants(pageable);

        return results.stream().map(obj -> {
            Restaurant r = (Restaurant) obj[0];
            Double moyenne = (Double) obj[1];
            String nom = (String) obj[2];

            return new TopRestaurantDTO(
                    r.getId(),
                    nom,
                    moyenne != null ? moyenne : 0.0
            );
        }).toList();
    }

}