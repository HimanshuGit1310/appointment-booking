package com.zeal.appointment_booking.service;
import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.dto.AppointmentRequestDto;
import com.zeal.appointment_booking.model.*;
import com.zeal.appointment_booking.repo.AppointmentRepo;
import com.zeal.appointment_booking.repo.DoctorSlotRepo;
import com.zeal.appointment_booking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorSlotRepo doctorSlotRepo;

    @Autowired
    private UserRepo userRepo;


    public ResponseEntity<ApiResponse<List<DoctorSlot>>> getAvailableSlot() {
        List<DoctorSlot> availableSlots = doctorSlotRepo.findByAvailableTrue();
        if (availableSlots.isEmpty()) {
            ApiResponse<List<DoctorSlot>> response = new ApiResponse<>("No Slot Available", null);
            return ResponseEntity.ok(response);
        }
        else {
            ApiResponse<List<DoctorSlot>> response = new ApiResponse<>("Success",availableSlots);
            return ResponseEntity.ok(response);
        }
    }



    public ResponseEntity<ApiResponse<Appointment>> createAppointment(AppointmentRequestDto dto) {

        Optional<DoctorSlot> doctorSlotOpt = doctorSlotRepo.findById(dto.getDoctorSlotId());
        Optional<Users> patientUserOpt = userRepo.findById(dto.getPatientUserId());
        if (doctorSlotOpt.isEmpty()) {
            ApiResponse<Appointment> response = new ApiResponse<>("Slot does not exist",null);
            return ResponseEntity.ok(response);
        }

        if (patientUserOpt.isEmpty() ||  patientUserOpt.get().getRole() != Role.PATIENT) {
            ApiResponse<Appointment> response = new ApiResponse<>("Patient does not exist",null);
            return ResponseEntity.ok(response);
        }

        DoctorSlot slot = doctorSlotOpt.get();
        if (!slot.getAvailable()) {
            ApiResponse<Appointment> response = new ApiResponse<>("Slot is Unavailable",null);
            return ResponseEntity.ok(response);
        }

        Users patientUser = patientUserOpt.get();
        Patient patient = patientUser.getPatient();

        if (patient == null){
            ApiResponse<Appointment> response = new ApiResponse<>("Patient is Unavailable",null);
            return ResponseEntity.ok(response);
        }

        slot.setAvailable(false);
        doctorSlotRepo.save(slot);


        Appointment newAppointment = new Appointment();
        newAppointment.setStatus(true);
        newAppointment.setDoctorSlot(slot);
        newAppointment.setPatient(patient);
        appointmentRepo.save(newAppointment);

        ApiResponse<Appointment> response = new ApiResponse<>("Booked Successfully",newAppointment);
        return ResponseEntity.ok(response);
    }

//    public ResponseEntity<ApiResponse<?>> deleteAppointment(int id) {
//      if (!appointmentRepo.existsById(id)) {
//          return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                  .body(new ApiResponse<>("Appointment not found with id " + id, null));
//      }
//      Appointment appointment = appointmentRepo.findById(id).get();
//      Optional<DoctorSlot> optionalSlot = doctorSlotRepo.findById(appointment.getDoctorSlotId());
//
//      if (optionalSlot.isPresent()){
//          DoctorSlot slot = optionalSlot.get();
//          slot.setAvailable(true);
//          doctorSlotRepo.save(slot);
//      }
//
//      appointmentRepo.deleteById(id);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new ApiResponse<>("Deleted Successfully ! ",null));
//    }
//
//
}
