package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
    @NotBlank @Size(min = 1, max = 25) String username, 
    @NotBlank String password, 
    @NotBlank @Size(min = 1, max = 45) @Email String email
) {
}
