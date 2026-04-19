package com.springboot.march_24_1sb.dto;

public record CustomerResponseDto(
        long id ,
        String name ,
        String email,
        String city
) {
}
