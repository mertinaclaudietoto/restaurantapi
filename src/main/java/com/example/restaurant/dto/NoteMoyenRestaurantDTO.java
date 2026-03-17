package com.example.restaurant.dto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class NoteMoyenRestaurantDTO {
    Long id;
    String restaurant;
    Double noteMoyenne;
    Long nombreAvis;
    
}
