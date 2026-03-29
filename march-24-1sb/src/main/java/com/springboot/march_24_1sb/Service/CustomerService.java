package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.CustomerMapper;
import com.springboot.march_24_1sb.Mapper.UserMapper;
import com.springboot.march_24_1sb.Repository.CustomerRepository;
import com.springboot.march_24_1sb.dto.CustomerDto;
import com.springboot.march_24_1sb.dto.CustomerReqDto;
import com.springboot.march_24_1sb.dto.CustomerSignUpDto;
import com.springboot.march_24_1sb.enums.Role;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.model.Customer;
import com.springboot.march_24_1sb.model.Users;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

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

    public Customer getCustomerByIdIntEnt(long customerid) {
        return customerRepository.findById(customerid)
                .orElseThrow(() -> new ResourceNotFound("Invalid id"));
    }

    public void signUpCustomer(@Valid CustomerSignUpDto customerSignUpDto) {
        Customer customer = CustomerMapper.CustomerSignUpDtoToEnt(customerSignUpDto);

        Users users = UserMapper.CustomerSignUpToEnt(customerSignUpDto);

        users.setRole(Role.CUSTOMER);
        users.setPassword(passwordEncoder.encode(customerSignUpDto.password()));

        Users users1 = userService.saveUsers(users);

        customer.setUsers(users1);

        customerRepository.save(customer);



    }
}
