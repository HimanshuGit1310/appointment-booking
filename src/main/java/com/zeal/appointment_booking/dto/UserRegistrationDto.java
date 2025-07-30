package com.zeal.appointment_booking.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserRegistrationDTO {

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
