package com.zeal.appointment_booking.service;
import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.model.Appointment;
import com.zeal.appointment_booking.model.DoctorSlot;
import com.zeal.appointment_booking.repo.AppointmentRepo;
import com.zeal.appointment_booking.repo.DoctorSlotRepo;
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


    public ResponseEntity<ApiResponse<List<DoctorSlot>>> getAvailableSlot() {
        List<DoctorSlot> availableSlots = doctorSlotRepo.findByAvailableTrue();
        if (availableSlots.isEmpty())
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("No Available slot found",null));
        else
            return ResponseEntity.ok(new ApiResponse<>("Slots Found",availableSlots));
    }



    public ResponseEntity<ApiResponse<Appointment>> createAppointment(Appointment appointment) {

        Optional<DoctorSlot> doctorSlot = doctorSlotRepo.findById(appointment.getDoctorSlotId());
        if (doctorSlot.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("This slot not exists", null));
        }
        DoctorSlot slot = doctorSlot.get();
        if (!slot.getAvailable())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Slot is already Booked",null));
        slot.setAvailable(false);
        doctorSlotRepo.save(slot);

        Appointment newAppointment = new Appointment();
        newAppointment.setDoctorSlotId(slot.getId());
        newAppointment.setPatientId(appointment.getPatientId());
        newAppointment.setStatus(true);

        appointmentRepo.save(newAppointment);
        return ResponseEntity.ok(new ApiResponse<>("Appointment Booked",newAppointment));
    }


    public ResponseEntity<ApiResponse<?>> deleteAppointment(int id) {
      if (!appointmentRepo.existsById(id)) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body(new ApiResponse<>("Appointment not found with id " + id, null));
      }
      Appointment appointment = appointmentRepo.findById(id).get();
      Optional<DoctorSlot> optionalSlot = doctorSlotRepo.findById(appointment.getDoctorSlotId());

      if (optionalSlot.isPresent()){
          DoctorSlot slot = optionalSlot.get();
          slot.setAvailable(true);
          doctorSlotRepo.save(slot);
      }

      appointmentRepo.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("Deleted Successfully",null));
    }
}
