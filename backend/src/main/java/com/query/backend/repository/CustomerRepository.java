package com.query.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.query.backend.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
}