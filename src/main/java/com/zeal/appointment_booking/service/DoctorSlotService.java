package com.zeal.appointment_booking.service;

import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.dto.DoctorSlotRequestDto;
import com.zeal.appointment_booking.model.Doctor;
import com.zeal.appointment_booking.model.DoctorSlot;
import com.zeal.appointment_booking.model.Role;
import com.zeal.appointment_booking.model.Users;
import com.zeal.appointment_booking.repo.DoctorSlotRepo;
import com.zeal.appointment_booking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorSlotService {

    @Autowired
    private DoctorSlotRepo doctorSlotRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<List<DoctorSlot>> allDoctorSlot(){
        List<DoctorSlot> doctorSlotList = doctorSlotRepo.findAll();
        return new ResponseEntity<>(doctorSlotList,HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<DoctorSlot>> addDoctorSlot(DoctorSlotRequestDto dto) {
            Optional<Users> doctorOpt = userRepo.findById(dto.getDoctorId());
            if (doctorOpt.isEmpty() || doctorOpt.get().getRole() != Role.DOCTOR){
                ApiResponse<DoctorSlot> response = new ApiResponse<>("Doctor Id is Invalid",null);
                return ResponseEntity.ok(response);
            }
            DoctorSlot doctorSlot = new DoctorSlot();
            doctorSlot.setDateTime(dto.getDateTime());
            doctorSlot.setAvailable(dto.getAvailable());
            doctorSlot.setDoctor(doctorOpt.get());
            doctorSlotRepo.save(doctorSlot);

            ApiResponse<DoctorSlot> response = new ApiResponse<>("Slot Added Successfully",doctorSlot);
            return ResponseEntity.ok(response);
        }


    public ResponseEntity<String> updateSlot(int id, DoctorSlot doctorSlot) {
        Optional<DoctorSlot> existingDoctorSlot = doctorSlotRepo.findById(id);
        if (existingDoctorSlot.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Not Found");
        }
        DoctorSlot doctorSlot1 = existingDoctorSlot.get();
        doctorSlot1.setDateTime(doctorSlot.getDateTime());
        doctorSlot1.setAvailable(doctorSlot.getAvailable());
        return ResponseEntity.status(HttpStatus.OK).body("SuccessFully Updated");
        
    }

    public ResponseEntity<String> deleteSlot(int id) {
        Optional<DoctorSlot> doctorSlot = doctorSlotRepo.findById(id);
        if (doctorSlot.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Slot Not Found");
        }
        DoctorSlot slot = doctorSlot.get();
        doctorSlotRepo.delete(slot);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
