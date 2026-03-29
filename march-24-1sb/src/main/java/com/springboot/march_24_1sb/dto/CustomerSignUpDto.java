package com.springboot.march_24_1sb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerSignUpDto(
        @NotNull
        @NotBlank
        String name,

        @Email
        String email,

        @NotNull
        @NotBlank
        String city,

        @NotNull
        @NotBlank
        @Size(min = 4 , max = 30)
        String username,

        @Size(min = 6 , max = 30)
        String password

) {
}
