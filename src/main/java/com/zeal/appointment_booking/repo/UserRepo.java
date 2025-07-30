package com.zeal.appointment_booking.repo;

import com.zeal.appointment_booking.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    boolean existsByUsername(String username);
}
