package com.springboot.march_24_1sb.Mapper;

import com.springboot.march_24_1sb.dto.ExecutiveDto;
import com.springboot.march_24_1sb.model.Executive;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExecutiveMapper {
    public static Executive ExecDtoToEnt(ExecutiveDto executiveDto){
        Executive executive = new Executive();
        executive.setName(executiveDto.name());
        executive.setJobTitle(executiveDto.jobTitle());

        return executive;
    }


    public static ExecutiveDto EntToDto(Executive executive){
        return new ExecutiveDto(
                executive.getName(),
                executive.getJobTitle()
        );
    }
}
