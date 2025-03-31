package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTopicDTO(
    @NotBlank @Size(min = 1, max = 45) String title,
    @NotBlank @Size(min = 1, max = 2000) String message,
    @NotNull short categoryId
) {

}
