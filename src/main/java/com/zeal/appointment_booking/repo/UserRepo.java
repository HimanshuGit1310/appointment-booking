package com.zeal.appointment_booking.repo;

import com.zeal.appointment_booking.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
