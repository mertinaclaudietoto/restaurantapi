package com.example.restaurant.controller; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.ApiResponse;
import com.example.restaurant.dto.AvisDTO;
import com.example.restaurant.dto.NoteMoyenRestaurantDTO;
import com.example.restaurant.dto.PlatDTO;
import com.example.restaurant.dto.RestaurantDTO;
import com.example.restaurant.dto.TopRestaurantDTO;
import com.example.restaurant.model.Avis;
import com.example.restaurant.model.Plat;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.service.AvisService;
import com.example.restaurant.service.PlatService;
import com.example.restaurant.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService _service;
    private final PlatService _servicePlat;
    private final AvisService _serviceAvis;


    @GetMapping
    public ResponseEntity<ApiResponse<Page<Restaurant>>> getRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = _service.getAll(pageable);
        ApiResponse<Page<Restaurant>> response = new ApiResponse<>(200, "List restaurants selected successfully", restaurants);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Restaurant>> create(@Valid @RequestBody RestaurantDTO  user) {
      
        Restaurant updated =_service.save(user); // peut lancer NotFoundException
        ApiResponse<Restaurant> response = new ApiResponse<>(200, "Restaurant add successfully", updated);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/plats")
    public ResponseEntity<ApiResponse<Plat>> createPlats(@PathVariable Long id,@Valid @RequestBody PlatDTO  value) {
        Plat updated =_servicePlat.save(id,value); // peut lancer NotFoundException
        ApiResponse<Plat> response = new ApiResponse<>(200, "Plat add successfully", updated);
        return ResponseEntity.ok(response);
    }

   

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return _service.get(id);
    }
    @GetMapping("/{id}/plats")
    public ResponseEntity<ApiResponse<Page<Plat>>> getPlatsRestaurant(@PathVariable Long id,@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Plat> restaurants = _servicePlat.getAll(id,pageable);
        ApiResponse<Page<Plat>> response = new ApiResponse<>(200, "List restaurants selected successfully", restaurants);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        _service.deleteRestaurant(id);
    }


    @PostMapping("/{id}/avis")
    public ResponseEntity<ApiResponse<Avis>> createAvis(@PathVariable Long id,@Valid @RequestBody AvisDTO  value) {
        Avis updated =_serviceAvis.save(id,value);
        ApiResponse<Avis> response = new ApiResponse<>(200, "Avis add successfully", updated);
        return ResponseEntity.ok(response);
    }

     @GetMapping("/{id}/avis")
    public ResponseEntity<ApiResponse<Page<Avis>>> getAvis(@PathVariable Long id,@PageableDefault(page = 0, size = 10) Pageable pageable) {
         Page<Avis> updated =_serviceAvis.getAll(id,pageable);
        ApiResponse<Page<Avis>> response = new ApiResponse<>(200, "Avis selected successfully", updated);
        return ResponseEntity.ok(response);
    }
    
     @GetMapping("/{id}/note-moyenne-et-avis")
    public ResponseEntity<ApiResponse<NoteMoyenRestaurantDTO>> getNoteMoyenneEtNombreAvis(
            @PathVariable("id") Long restaurantId) {

        NoteMoyenRestaurantDTO dto = _service.getNoteMoyenneEtNombreAvis(restaurantId);
        ApiResponse<NoteMoyenRestaurantDTO> response = new ApiResponse<>(200, "Avis selected successfully", dto);
        
        return ResponseEntity.ok(response);
    }
   
    @GetMapping("/top")
    public ResponseEntity<ApiResponse<List<TopRestaurantDTO>> > getTopRestaurants(Pageable pageable) {

        List<TopRestaurantDTO> top = _service.getTopRestaurants(pageable);
        ApiResponse<List<TopRestaurantDTO>> response = new ApiResponse<>(200, "Avis selected successfully", top);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<EntityModel<Map<String, Object>>> getRestaurant(@PathVariable Long id) {

        Restaurant restaurant = _service.get(id);
        // Corps de base
        Map<String, Object> body = new HashMap<>();
        body.put("id", restaurant.getId());
        body.put("nom", restaurant.getNom());
        body.put("address", restaurant.getAdresse());
        body.put("ville", restaurant.getVille());


        EntityModel<Map<String, Object>> resource = EntityModel.of(body);
        resource.add(
            linkTo(methodOn(RestaurantController.class).getById(id)).withSelfRel()
        );

        resource.add(
            linkTo(methodOn(RestaurantController.class).getPlatsRestaurant(id, null))
            .withRel("plats")
        );

        resource.add(
            linkTo(methodOn(RestaurantController.class).createAvis(id, null))
            .withRel("avis")
        );

        return ResponseEntity.ok(resource);
    }

    @GetMapping("filter")
    public ResponseEntity<Page<Restaurant>> getRestaurants(
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Double noteMin,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<Restaurant> result = _service.searchRestaurants(ville, nom, noteMin, pageable);
        return ResponseEntity.ok(result);
    }
}