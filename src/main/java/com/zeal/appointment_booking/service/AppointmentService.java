package com.zeal.appointment_booking.service;

import com.zeal.appointment_booking.model.Appointment;
import com.zeal.appointment_booking.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    public ResponseEntity<List<Appointment>> allAppointment() {
        List<Appointment> appointments = appointmentRepo.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    public ResponseEntity<Appointment> getAppointmentById(int id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Appointment Not found by this ID " + id));
        return new ResponseEntity<>(appointment, HttpStatus.FOUND);

    }

    public ResponseEntity<Appointment> addAppointment(Appointment appointment) {
        Appointment appointment1 = appointmentRepo.save(appointment);
        return new ResponseEntity<>(appointment1, HttpStatus.CREATED);
    }

    public ResponseEntity<Appointment> updateAppointment(int id, Appointment updateData) {

        Appointment existingApp = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));

        existingApp.setId(updateData.getId());
        existingApp.setDoctorSlotId(updateData.getDoctorSlotId());
        existingApp.setPatientSlotId(updateData.getPatientSlotId());
        existingApp.setStatus(updateData.isStatus());

        Appointment updatedApp = appointmentRepo.save(existingApp);
        return new ResponseEntity<>(updatedApp, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteAppointment(int id) {
        if (!appointmentRepo.existsById(id))
            return new ResponseEntity<>("Appointment Not Found With this ID "+ id,HttpStatus.NOT_FOUND);
        else {
            appointmentRepo.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
    }
}
