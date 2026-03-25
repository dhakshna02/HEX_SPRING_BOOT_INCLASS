package com.springboot.march_24_1sb.Mapper;

import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.dto.CustomerReqDto;
import com.springboot.march_24_1sb.model.Customer;
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



}
