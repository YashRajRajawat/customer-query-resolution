package com.query.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.query.backend.entity.Admins;

public interface AdminRepository extends JpaRepository<Admins, Long> {
    Optional<Admins> findByEmail(String email);
}
