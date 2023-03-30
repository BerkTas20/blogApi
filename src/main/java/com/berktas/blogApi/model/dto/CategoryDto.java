package com.berktas.blogApi.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDto(
        Long id,
        @NotBlank
        @Size(min = 4,message = "Min size of category title is 4")
        String title,

        @NotBlank
        @Size(min = 10, message = "min size of category desc is 10")
        String description) {

}
