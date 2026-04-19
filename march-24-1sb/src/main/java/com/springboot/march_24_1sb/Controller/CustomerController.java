package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Mapper.CustomerMapper;
import com.springboot.march_24_1sb.Service.CustomerService;
import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.dto.CustomerReqDto;
import com.springboot.march_24_1sb.dto.CustomerResponseDto;
import com.springboot.march_24_1sb.dto.CustomerSignUpDto;
import com.springboot.march_24_1sb.model.Customer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin("http://localhost:5173/")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created customer successfully");
    }


    @GetMapping("/get-by-id/{id}")
    public CustomerDto getCustomerById(@PathVariable long id ){
        return customerService.getCustomerById(id);
    }


    // There exist already a CustomerDto that has enity of customer
    // using that CustomerDto we are creating new dto whcih is CustomerReqDto
    // in this we change to List<CustomerDto> so that we can show that in ui and we will not
    // expose the db


    @GetMapping("/get-all")
    public CustomerReqDto getCustomers(@RequestParam(value = "page", required = false , defaultValue = "0") int page ,
                                       @RequestParam(value = "size", required = false,defaultValue = "5") int size){
        return customerService.getCustomers(page,size);
    }


    // signup feature

    @PostMapping("/signup")
    public ResponseEntity<?> signUpCustomer(@Valid @RequestBody CustomerSignUpDto customerSignUpDto){

        customerService.signUpCustomer(customerSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/get-one")
    public CustomerResponseDto getCustomer(Principal principal){
    Customer customer =     customerService.getCustomerByUserName(principal.getName());
    return CustomerMapper.custEmttoDtoo(customer);
    }


    @GetMapping("getall")
    public List<Customer> getall(){
        return customerService.getAll();
    }
}
