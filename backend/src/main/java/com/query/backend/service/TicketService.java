package com.query.backend.service;

import org.springframework.stereotype.Service;

import com.query.backend.dto.QueryRequest;
import com.query.backend.entity.Customers;
import com.query.backend.entity.TicketStatus;
import com.query.backend.entity.Tickets;
import com.query.backend.repository.CustomerRepository;
import com.query.backend.repository.TicketRepository;

@Service
public class TicketService {
    
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    public TicketService(CustomerRepository customerRepository,
                         TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
    }

    public String createTicket(QueryRequest request) {
        Customers customer = customerRepository
            .findByEmail(request.getEmail())
            .orElseGet(() -> {
                Customers newCustomer = new Customers();
                newCustomer.setName(request.getName());
                newCustomer.setEmail(request.getEmail());
                newCustomer.setContact(request.getContact());
                return customerRepository.save(newCustomer);
            });

        Tickets ticket = new Tickets();
        ticket.setQuery(request.getQuery());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCustomer(customer);

        Tickets savedTicket = ticketRepository.save(ticket);

        String date = java.time.LocalDate.now()
                .format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE);

        String ticket_id = "TCK-" + date + "-" +
                String.format("%04d", savedTicket.getId());
        
        savedTicket.setTicketId(ticket_id);

        ticketRepository.save(savedTicket);

        return ticket_id;
    }
}