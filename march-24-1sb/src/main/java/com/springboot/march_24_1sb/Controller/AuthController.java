package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.config.JwtFilter;
import com.springboot.march_24_1sb.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JWTUtil jwtUtil;

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){

        System.out.println("api ->works"+principal.getName());
        String loggedin = principal.getName();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtUtil.generateToken(loggedin));
        System.out.println(map);
        return ResponseEntity.status(HttpStatus.OK).body(map);

    }
}
