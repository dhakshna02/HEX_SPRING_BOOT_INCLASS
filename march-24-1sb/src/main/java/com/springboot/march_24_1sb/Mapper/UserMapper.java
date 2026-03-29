package com.springboot.march_24_1sb.Mapper;

import com.springboot.march_24_1sb.dto.CustomerSignUpDto;
import com.springboot.march_24_1sb.model.Users;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserMapper {


    public static Users CustomerSignUpToEnt(@Valid CustomerSignUpDto customerSignUpDto) {

        Users user = new Users();
        user.setUsername(customerSignUpDto.username());

        return user;
    }
}
