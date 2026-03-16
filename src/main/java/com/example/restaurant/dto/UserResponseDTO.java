package com.example.restaurant.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String pseudo;
    private String email;
    private Long idrole;
}