package com.springboot.march_24_1sb.dto;

import com.springboot.march_24_1sb.enums.JobTitle;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExecutiveDto(

        @NotNull
        @NotBlank
        @Size(min=3, max = 255)
        String name,

        JobTitle jobTitle
) {
}
