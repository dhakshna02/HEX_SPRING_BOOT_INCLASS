package com.springboot.march_24_1sb.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class CustomerPlanUtil {

    public static LocalDate getEndDate(LocalDate CreatedAt, int days) {
        return CreatedAt.plusDays(days);

    }
}
