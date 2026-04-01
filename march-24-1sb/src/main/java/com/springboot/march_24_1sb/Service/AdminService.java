package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Repository.UserRepository;
import com.springboot.march_24_1sb.dto.AdminDto;
import com.springboot.march_24_1sb.enums.Role;
import com.springboot.march_24_1sb.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void addAdmin(AdminDto adminDto) {
        Users user = new Users();
        user.setUsername(adminDto.username());
        user.setPassword(passwordEncoder.encode(adminDto.password()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);



    }
}
