package com.springboot.march_24_1sb.Mapper;

import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.dto.CustomerReqDto;
import com.springboot.march_24_1sb.dto.CustomerResponseDto;
import com.springboot.march_24_1sb.dto.CustomerSignUpDto;
import com.springboot.march_24_1sb.model.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomerMapper {

    public static Customer CustDtoToEnt(CustomerDto customerDto) {

        Customer customer = new Customer();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setCity(customerDto.city());

        return customer;
    }

    public static CustomerDto CustEntToDto(Customer cust){
        return new CustomerDto(
                cust.getName(),
                cust.getEmail(),
                cust.getCity()
        );
    }


    public static Customer CustomerSignUpDtoToEnt(@Valid CustomerSignUpDto customerSignUpDto) {

        Customer customer = new Customer();
        customer.setName(customerSignUpDto.name());
        customer.setEmail(customerSignUpDto.email());
        customer.setCity(customerSignUpDto.city());

        return customer;

    }



    public static CustomerResponseDto custEmttoDtoo(Customer customer){
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCity()
        );
    }
}
