package com.zeal.appointment_booking.controller;

import com.zeal.appointment_booking.model.DoctorSlot;
import com.zeal.appointment_booking.service.DoctorSlotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctorSlot")
public class DoctorSlotController {

    @Autowired
    private DoctorSlotService doctorSlotService;

    @GetMapping("/all")
    public ResponseEntity<List<DoctorSlot>> allDoctorSlots(){
        return doctorSlotService.allDoctorSlot();
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorSlot> addDoctorSlot(@RequestBody DoctorSlot doctorSlot){
        return doctorSlotService.addDoctorSlot(doctorSlot);
    }



}
