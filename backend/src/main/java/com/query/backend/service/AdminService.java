package com.query.backend.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.query.backend.dto.LoginRequest;
import com.query.backend.dto.RegistrationRequest;
import com.query.backend.entity.Admins;
import com.query.backend.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    
    public ResponseEntity<String> register(RegistrationRequest request) {
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password and Confirm Password do not match");
        }

        Admins admin = new Admins();
        admin.setName(request.getName());
        admin.setContact(request.getContact());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        adminRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully");
    }

    public ResponseEntity<String> login(LoginRequest request) {
        Optional<Admins> adminOpt = adminRepository.findByEmail(request.getEmail());

        if (adminOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        Admins admin = adminOpt.get();

        if (!admin.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        return ResponseEntity.ok("Admin login successful");
    }
}
