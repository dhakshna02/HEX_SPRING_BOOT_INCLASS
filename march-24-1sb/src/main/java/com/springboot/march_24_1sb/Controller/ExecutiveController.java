package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.ExecutiveService;
import com.springboot.march_24_1sb.dto.ExecReqDto;
import com.springboot.march_24_1sb.dto.ExecutiveDto;
import com.springboot.march_24_1sb.model.Executive;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/executive")
public class ExecutiveController {
    private final ExecutiveService executiveService;

    @PostMapping("/api/saveExecutive")
    public ResponseEntity<?> saveExecutive(@Valid @RequestBody ExecutiveDto executiveDto){
        executiveService.saveExecutive(executiveDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-by-id/{id}")
    public ExecutiveDto getExecutiveByID(@PathVariable long id ){

        return executiveService.getExecutiveByID(id);

    }



    @GetMapping("/get-by-filter/{jobTitle}")
    public List<ExecutiveDto> getExecutiveByFilter(@PathVariable String jobTitle){
        return executiveService.getExecutiveByFilter(jobTitle);
    }


    @GetMapping("/get")
    public ExecReqDto getAllExecutive(@RequestParam(value = "page",defaultValue = "0",required = false) int page,
                                      @RequestParam(value = "size",defaultValue = "5",required = false) int size){
        return executiveService.getAllExecutive(page,size);
    }
}
