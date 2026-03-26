package com.springboot.march_24_1sb.dto;

import com.springboot.march_24_1sb.enums.JobTitle;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Customer;
import com.springboot.march_24_1sb.model.Executive;

import java.time.Instant;

public record DtoForGetAllByCustomer_ForRealtionship(
                long  id,
                String subject,
                TicketStatus status,
                TicketPriority priority,
                Instant createdAt,
                String customerName,
                String executiveName,
                JobTitle executiveJobTitle
) {
}
