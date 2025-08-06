package com.zeal.appointment_booking.controller;

import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.dto.UserRegistrationDto;
import com.zeal.appointment_booking.model.Users;
import com.zeal.appointment_booking.service.JwtService;
import com.zeal.appointment_booking.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody UserRegistrationDto dto){
        return userService.register(dto);
    }

    @PostMapping("/login")
    public String verify(@RequestBody UserRegistrationDto dto){
        return userService.verify(dto);
    }

}
