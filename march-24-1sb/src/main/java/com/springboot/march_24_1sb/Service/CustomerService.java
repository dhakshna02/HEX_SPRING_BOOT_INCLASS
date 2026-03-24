package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.CustomerMapper;
import com.springboot.march_24_1sb.Repository.CustomerRepository;
import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.model.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void saveCustomer(@Valid CustomerDto customerDto) {
       Customer customer = CustomerMapper.CustDtoToEnt(customerDto);

       customerRepository.save(customer);

    }
}
