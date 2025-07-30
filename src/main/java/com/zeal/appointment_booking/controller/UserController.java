package com.zeal.appointment_booking.controller;

import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.dto.UserRegistrationDto;
import com.zeal.appointment_booking.model.Users;
import com.zeal.appointment_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String login(@RequestBody Users user){
        return userService.login(user);
    }

}
