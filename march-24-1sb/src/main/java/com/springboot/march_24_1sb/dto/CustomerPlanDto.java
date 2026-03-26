package com.springboot.march_24_1sb.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public record CustomerPlanDto(
        LocalDate createdAt,
        BigDecimal discount,
        String coupon
) {
}
