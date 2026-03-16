package com.example.restaurant.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoriDTO {

    private Long id;

    @NotNull(message = "L'id user est obligatoire")
    private Long userId;

    @NotNull(message = "L'id restaurant est obligatoire")
    private Long  restaurantId;


}