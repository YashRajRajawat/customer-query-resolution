package com.query.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.query.backend.entity.Tickets;

public interface TicketRepository extends JpaRepository<Tickets, Long> {
    Optional<Tickets> findByTicketId(String ticketId);
}