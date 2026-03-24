package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.ExecutiveService;
import com.springboot.march_24_1sb.dto.ExecutiveDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExecutiveController {
    private final ExecutiveService executiveService;

    @PostMapping("/api/saveExecutive")
    public ResponseEntity<?> saveExecutive(@Valid @RequestBody ExecutiveDto executiveDto){
        executiveService.saveExecutive(executiveDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
