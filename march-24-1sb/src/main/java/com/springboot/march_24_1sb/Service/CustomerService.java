package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.CustomerMapper;
import com.springboot.march_24_1sb.Repository.CustomerRepository;
import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.dto.CustomerReqDto;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.model.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void saveCustomer(@Valid CustomerDto customerDto) {
       Customer customer = CustomerMapper.CustDtoToEnt(customerDto);

       customerRepository.save(customer);

    }

    public CustomerDto getCustomerById(long id) {
       Customer customer =  customerRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFound("Invalid Id"));

       return new CustomerDto(
               customer.getName(),
               customer.getEmail(),
               customer.getCity()
       );
    }

    public CustomerReqDto getCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Customer> customerList = customerRepository.findAll(pageable);

        List<CustomerDto> customerDtos = customerList.toList()
                .stream().map(CustomerMapper :: CustEntToDto ).toList();

        return new CustomerReqDto(
                customerDtos,
                customerList.getTotalPages(),
                customerList.getTotalElements()
        );
    }
}
