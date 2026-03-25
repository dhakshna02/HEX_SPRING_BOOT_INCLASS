package com.springboot.march_24_1sb.dto;

import java.util.List;

public record ExecReqDto(
        List<ExecutiveDto> exdto,
        int TotalPage,
        Long TotalElements
) {
}
