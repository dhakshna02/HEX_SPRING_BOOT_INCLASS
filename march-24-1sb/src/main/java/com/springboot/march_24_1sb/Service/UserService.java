package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Repository.UserRepository;
import com.springboot.march_24_1sb.model.Users;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Users saveUsers(Users users) {
      return   userRepository.save(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.FindByUserName(username);
        return users;
    }
}
