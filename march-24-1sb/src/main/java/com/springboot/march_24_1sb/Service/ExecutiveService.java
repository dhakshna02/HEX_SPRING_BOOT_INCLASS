package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.ExecutiveMapper;
import com.springboot.march_24_1sb.Repository.ExecutiveRepository;
import com.springboot.march_24_1sb.dto.ExecutiveDto;
import com.springboot.march_24_1sb.model.Executive;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExecutiveService {

    private final ExecutiveRepository executiveRepository;
    public void saveExecutive(ExecutiveDto executiveDto) {

        Executive executive = ExecutiveMapper.ExecDtoToEnt(executiveDto);

        executiveRepository.save(executive);


    }
}
