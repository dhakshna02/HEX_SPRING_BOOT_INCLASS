package com.springboot.march_24_1sb.dto;

import com.springboot.march_24_1sb.enums.TicketPriority;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketDto(
        @NotNull
        @NotBlank
        @Size(min = 3,  max= 255)
        String subject,

        @NotBlank
        @NotNull
        @Size(min=3 , max = 1000)
        String details,

        TicketPriority ticket
) {
}
