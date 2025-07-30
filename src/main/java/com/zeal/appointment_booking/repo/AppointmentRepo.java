package com.zeal.appointment_booking.repo;

import com.zeal.appointment_booking.model.Appointment;
import com.zeal.appointment_booking.model.DoctorSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
//    List<DoctorSlot> getSlotByDoctId(int doctId);
}
