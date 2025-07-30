package com.zeal.appointment_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String designation;
    private String speciality;
    private String qualification;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users doctors;


}
