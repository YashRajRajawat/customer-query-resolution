package com.query.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.query.backend.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByEmail(String email);
}