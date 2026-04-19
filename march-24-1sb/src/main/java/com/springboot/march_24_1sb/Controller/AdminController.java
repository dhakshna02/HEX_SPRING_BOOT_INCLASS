package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.AdminService;
import com.springboot.march_24_1sb.dto.AdminDto;
import com.springboot.march_24_1sb.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody AdminDto adminDto){

        adminService.addAdmin(adminDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




}
