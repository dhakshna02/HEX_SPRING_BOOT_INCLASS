package com.springboot.march_24_1sb.config;


import com.springboot.march_24_1sb.Service.UserService;
import com.springboot.march_24_1sb.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class Securityconfig {

    private final UserService userService;


    // phase 1 : just add user and the password with their authority
//@Bean
//    public UserDetailsService users() {
//        UserDetails manager = User.builder()
//                .username("Dhakshna")
//                .password("{noop}Dhakshna")
//                .authorities("MANAGER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("Bubu")
//                .password("{noop}bubu")
//                .authorities("ADMIN")
//                .build();
//
//        UserDetails apprentice = User.builder()
//                .username("sid")
//                .password("{noop}sid")
//                .authorities("APRENTICE")
//                .build();
//
//        return new InMemoryUserDetailsManager(manager, admin,apprentice);
//
//
//    }
//
//    // secure the endpoints as per the requirement - securityFilterChain
    @Bean
        public SecurityFilterChain securedFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(Customizer.withDefaults())
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/api/customer/signup")
                            .permitAll()
                            .requestMatchers("/api/customer/get-all")
                            .permitAll()
                            .requestMatchers("/api/customer/save")
                            .permitAll()
                            .requestMatchers("/api/customer/get-by-id/{id}")
                            .hasAuthority("CUSTOMER")
                            .requestMatchers("/api/ticket/get-by-id/{id}")
                            .authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());
            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }


        @Bean
        public AuthenticationManager Authenticationmanager(
                                                            UserDetailsService userDetailsService,
                                                            PasswordEncoder passwordEncoder){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);

        }
}


