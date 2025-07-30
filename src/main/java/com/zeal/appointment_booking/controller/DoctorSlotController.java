package com.zeal.appointment_booking.controller;

import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.dto.DoctorSlotRequestDto;
import com.zeal.appointment_booking.model.DoctorSlot;
import com.zeal.appointment_booking.service.DoctorSlotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorSlotController {

    @Autowired
    private DoctorSlotService doctorSlotService;

    @GetMapping("/allSlots")
    public ResponseEntity<List<DoctorSlot>> allDoctorSlots(){
        return doctorSlotService.allDoctorSlot();
    }

    @PostMapping("/createSlot")
    public ResponseEntity<ApiResponse<DoctorSlot>> addDoctorSlot(@RequestBody DoctorSlotRequestDto dto) {
        return doctorSlotService.addDoctorSlot(dto);
    }

    @PutMapping("/updateSlot/{id}")
    public ResponseEntity<String> updateSlot(@PathVariable int id,@RequestBody DoctorSlot doctorSlot){
        return doctorSlotService.updateSlot(id,doctorSlot);
    }

    @DeleteMapping("/deleteSlot/{id}")
    public ResponseEntity<String> deleteSlot(@PathVariable int id){
        return doctorSlotService.deleteSlot(id);
    }



}
