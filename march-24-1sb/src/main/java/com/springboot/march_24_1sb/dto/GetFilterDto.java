package com.springboot.march_24_1sb.dto;

import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;

public record GetFilterDto(
        String status,
        String priority
) {
}
