package com.zeal.appointment_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private DoctorSlot doctorSlot;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
