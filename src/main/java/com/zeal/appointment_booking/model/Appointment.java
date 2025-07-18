package com.zeal.appointment_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Data
@Component
public class Appointment {

    //id, doctorSlotId, patientId, status
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int doctorSlotId;
    private int patientSlotId;
    private boolean status;
}
