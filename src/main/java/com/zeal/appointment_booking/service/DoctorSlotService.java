package com.zeal.appointment_booking.service;

import com.zeal.appointment_booking.model.DoctorSlot;
import com.zeal.appointment_booking.repo.DoctorSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorSlotService {

    @Autowired
    private DoctorSlotRepo doctorSlotRepo;

    public ResponseEntity<List<DoctorSlot>> allDoctorSlot(){
        List<DoctorSlot> doctorSlotList = doctorSlotRepo.findAll();
        return new ResponseEntity<>(doctorSlotList,HttpStatus.OK);
    }

    public ResponseEntity<DoctorSlot> addDoctorSlot(DoctorSlot doctorSlot) {
        DoctorSlot doctorSlot1 = doctorSlotRepo.save(doctorSlot);
            return new ResponseEntity<>(doctorSlot1, HttpStatus.CREATED);
        }



}
