package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostCommentDTO(
    @NotNull int topicId, 
    @NotBlank @Size(min = 1, max = 2000) String message
) {

}
