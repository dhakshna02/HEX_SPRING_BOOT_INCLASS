package com.springboot.march_24_1sb.dto;

import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;

import java.time.Instant;
import java.util.List;

public record TicketResDto(

        Long id,
        String  subject,
        TicketStatus status,
        TicketPriority priority,
        Instant CreatedAt




) {
}
