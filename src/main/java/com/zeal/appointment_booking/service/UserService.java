package com.zeal.appointment_booking.service;


import com.zeal.appointment_booking.dto.ApiResponse;
import com.zeal.appointment_booking.dto.UserRegistrationDto;
import com.zeal.appointment_booking.model.*;
import com.zeal.appointment_booking.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BCryptPasswordEncoder encoder ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @Transactional
    public ResponseEntity<ApiResponse<Users>> register(UserRegistrationDto dto) {
        if (userRepo.existsByUsername(dto.getUsername())){
            ApiResponse<Users> response = new ApiResponse<>("User Already Exist",null);
            return ResponseEntity.ok(response);
        }
        Users newUser = new Users();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setRole(dto.getRole());
        Users savedUser = userRepo.save(newUser);

        if (newUser.getRole() == Role.DOCTOR){
            Doctor doctor = new Doctor();
            doctor.setName(dto.getName());
            doctor.setDesignation(dto.getDesignation());
            doctor.setSpeciality(dto.getSpeciality());
            doctor.setQualification(dto.getQualification());
            doctor.setDoctors(savedUser);
            entityManager.persist(doctor);
        } else if (newUser.getRole() == Role.PATIENT) {
            Patient patient = new Patient();
            patient.setName(dto.getName());
            patient.setNumber(dto.getNumber());
            patient.setPatients(savedUser);
            entityManager.persist(patient);
        }
        ApiResponse<Users> response = new ApiResponse<>("Successfully registered",savedUser);
        return ResponseEntity.ok(response);
    }

    public String verify(UserRegistrationDto dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(dto.getUsername());
        }
        return "Login Failed";
    }
}
