package com.springboot.march_24_1sb.dto;

import com.springboot.march_24_1sb.model.Ticket;

import java.util.List;

public record TicketGetDto(
        List<TicketResDto> ticket,
        int TotalPages,
        Long TotalElements

) {
}
