package com.zeal.appointment_booking.controller;

import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.model.Appointment;
import com.zeal.appointment_booking.model.DoctorSlot;
import com.zeal.appointment_booking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userAppointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/availableSlots")
    public ResponseEntity<ApiResponse<List<DoctorSlot>>> getAvailableSlot(){
        return appointmentService.getAvailableSlot();
    }


    @PostMapping("/booking")
    public ResponseEntity<ApiResponse<Appointment>> createAppointment(@RequestBody Appointment appointment){
        return appointmentService.createAppointment(appointment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAppointment(@PathVariable int id){
        return appointmentService.deleteAppointment(id);
    }


}
