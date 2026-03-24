package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.CustomerService;
import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.model.Customer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/api/savecustomer")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created customer successfully");
    }
}
