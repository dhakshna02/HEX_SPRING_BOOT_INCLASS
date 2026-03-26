package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.ExecutiveMapper;
import com.springboot.march_24_1sb.Mapper.TicketMapper;
import com.springboot.march_24_1sb.Repository.ExecutiveRepository;
import com.springboot.march_24_1sb.dto.ExecReqDto;
import com.springboot.march_24_1sb.dto.ExecutiveDto;
import com.springboot.march_24_1sb.enums.JobTitle;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.model.Executive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExecutiveService {

    private final ExecutiveRepository executiveRepository;

    public void saveExecutive(ExecutiveDto executiveDto) {

        Executive executive = ExecutiveMapper.ExecDtoToEnt(executiveDto);

        executiveRepository.save(executive);


    }

    public ExecutiveDto getExecutiveByID(long id) {

        Executive executiv = executiveRepository.findById(id).orElseThrow(()->new ResourceNotFound("Invalid Id"));
        return new ExecutiveDto(
                executiv.getName(),
                executiv.getJobTitle()
        );
    }

    public List<ExecutiveDto> getExecutiveByFilter(String jobTitle) {



       List<Executive> exList =  executiveRepository.getExecutiveByFilter(JobTitle.valueOf(jobTitle));


       return exList.stream().map(ExecutiveMapper::EntToDto).toList();
    }

    public ExecReqDto getAllExecutive(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

       Page<Executive> pagee =  executiveRepository.findAll(pageable);
       int totalPage = pagee.getTotalPages();
       Long TotalElements = pagee.getTotalElements();

       List<ExecutiveDto> executiveDtos = pagee.toList()
                                               .stream()
                                               .map(ExecutiveMapper ::EntToDto)
                                               .toList();

       return new ExecReqDto(
                executiveDtos,
               totalPage,
               TotalElements
       );
    }
    // Assign the exective to the ticket for that checking the exective exits first
    public  Executive getExecById(long execid) {
        return executiveRepository.getById(execid);
    }


}
