package com.example.restaurant.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;


@Data
public class AvisDTO{

    private Long id;

    @NotNull(message = "La note est obligatoire")
    @Positive(message = "La note doit être positive")
    @Max(value = 5, message = "La note ne peut pas dépasser 5")
    private Integer note;

    @NotNull(message = "Le commentaire  est obligatoire")
    private String commentaire;

    @NotNull(message = "L'id user  est obligatoire")
    private Long userId;

    @NotNull(message = "L'id restaurant  est obligatoire")
    private Long restaurantId;


}