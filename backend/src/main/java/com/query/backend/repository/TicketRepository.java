package com.query.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.query.backend.entity.Tickets;

public interface TicketRepository extends JpaRepository<Tickets, Long> {
}