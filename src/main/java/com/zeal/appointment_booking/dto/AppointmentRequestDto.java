package com.zeal.appointment_booking.dto;

import lombok.Data;

@Data
public class AppointmentRequestDto {

    private int doctorSlotId;
    private int patientUserId;

}
