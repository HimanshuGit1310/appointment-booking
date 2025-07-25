package com.zeal.appointment_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@RequiredArgsConstructor
public class Appointment {

    //id, doctorSlotId, patientId, status
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int doctorSlotId;
    private int patientId;
    private boolean status;
}
