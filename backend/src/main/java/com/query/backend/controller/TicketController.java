package com.query.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.query.backend.entity.TicketStatus;
import com.query.backend.entity.Tickets;

import com.query.backend.repository.TicketRepository;

@RestController
@RequestMapping("/api/admin/tickets")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public List<Tickets> sendTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/{ticketId}")
    public Tickets getTicketById(@PathVariable String ticketId) {

        Tickets ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == TicketStatus.OPEN) {
            ticket.setStatus(TicketStatus.IN_PROGRESS);
            ticketRepository.save(ticket);
        }

        return ticket;
    }

    @PostMapping("/{ticketId}/resolve")
    public void resolveTicket(@PathVariable String ticketId) {

        Tickets ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(TicketStatus.CLOSED);
        ticketRepository.save(ticket);
    }
}