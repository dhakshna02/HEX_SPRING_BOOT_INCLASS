package com.springboot.march_24_1sb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerDto(

        @NotNull
        @NotBlank
        @Size(min=3, max = 255)
        String name,


        @NotNull
        @NotBlank
        @Size(min=3, max = 255)
        String email,

        @NotNull
        @NotBlank
        @Size(min=3, max = 255)
         String city
) {
}
