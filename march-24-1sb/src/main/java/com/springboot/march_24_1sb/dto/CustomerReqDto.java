package com.springboot.march_24_1sb.dto;

import java.util.List;

public record CustomerReqDto(
        List<CustomerDto> customerReqDto,
        int TotalPages,
        long TotalElements

) {
}
