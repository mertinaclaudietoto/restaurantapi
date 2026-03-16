package com.example.restaurant.dto;


import com.example.restaurant.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Le pseudo est obligatoire")
    @Size(min = 3, max = 50, message = "Le pseudo doit contenir entre 3 et 50 caractères")
    private String pseudo;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @UniqueEmail
    private String email;

    
    private String idrole;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, max = 100, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;
}