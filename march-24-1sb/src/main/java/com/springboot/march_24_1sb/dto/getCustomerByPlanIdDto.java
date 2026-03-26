package com.springboot.march_24_1sb.dto;

import java.time.LocalDate;

public record getCustomerByPlanIdDto(
       long customerid,
       String CustomerName,
       String CustomerEmail,
       String city,
       LocalDate startDate,
       LocalDate EndDate,
       String planName


) {
}
