package com.zeal.appointment_booking.dto;

import lombok.Data;

@Data
public class DoctorSlotRequestDto {

    private int id;
    private String dateTime;
    private Boolean available;
    private int doctorId;


}
