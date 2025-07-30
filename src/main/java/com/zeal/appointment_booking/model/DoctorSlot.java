package com.zeal.appointment_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@RequiredArgsConstructor
@Entity

public class DoctorSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dateTime;
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Users doctor;

}
