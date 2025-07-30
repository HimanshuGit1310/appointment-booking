package com.zeal.appointment_booking.dto;

import com.zeal.appointment_booking.model.Role;
import lombok.Data;

@Data
public class UserRegistrationDto {

    private String username;
    private String password;
    private Role role;
    private String name;

    //patient
    private String number;

    //doctor
    private String designation;
    private String speciality;
    private String qualification;
}
