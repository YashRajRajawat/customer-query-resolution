package com.query.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.query.backend.entity.Admins;
import com.query.backend.repository.AdminRepository;
import com.query.backend.repository.TicketRepository;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/register")
    public String register(@RequestBody Admins admins) {
        adminRepository.save(admins);
        return "Admin registered successfully";
    }
    
    @PostMapping("/login")
    public String login(@RequestBody Admins admins) {
        //TODO: process POST request
        return "Admin login successful";
    }
    
}
