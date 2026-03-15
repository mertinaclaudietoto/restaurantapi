package com.example.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDTO {

    private Long id;
    @NotBlank(message = "Le nom du rôle est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom du rôle doit avoir entre 3 et 50 caractères")
    private String name;
}