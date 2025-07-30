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
@RequestMapping("patient")
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
//
//    @DeleteMapping("/deleteBooking/{id}")
//    public ResponseEntity<ApiResponse<?>> deleteAppointment(@PathVariable int id){
//        return appointmentService.deleteAppointment(id);
//    }
//
//    @GetMapping("/doctorSlot/{doctId}")
//    public List<DoctorSlot> getSlotByDoctId(@PathVariable int doctId){
//        return appointmentService.getSlotByDoctId(doctId);
//    }



}
