package com.query.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.query.backend.dto.LoginRequest;
import com.query.backend.dto.RegistrationRequest;
import com.query.backend.service.AdminService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;

    AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@RequestBody RegistrationRequest request) {
        return adminService.register(request);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestBody LoginRequest request) {
        return adminService.login(request);
    }
    
}
