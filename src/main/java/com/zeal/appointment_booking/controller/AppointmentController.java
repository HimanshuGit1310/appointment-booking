package com.zeal.appointment_booking.controller;

import com.zeal.appointment_booking.model.Appointment;
import com.zeal.appointment_booking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/allApp")
    public ResponseEntity<List<Appointment>> allAppointment(){
        return appointmentService.allAppointment();
    }

    @GetMapping("/getAppointment/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id){
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id,@RequestBody Appointment updateData){
        return appointmentService.updateAppointment(id,updateData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id){
        return appointmentService.deleteAppointment(id);
    }


}
