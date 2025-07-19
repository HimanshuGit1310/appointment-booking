package com.zeal.appointment_booking.repo;

import com.zeal.appointment_booking.model.DoctorSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorSlotRepo extends JpaRepository<DoctorSlot,Integer> {
    List<DoctorSlot> findByAvailableTrue();
}
