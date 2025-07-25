package com.zeal.appointment_booking.service;

import com.zeal.appointment_booking.model.UserPrinciple;
import com.zeal.appointment_booking.model.Users;
import com.zeal.appointment_booking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User Not Found");
        }
        return new UserPrinciple(user);
    }

}
