package com.query.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.query.backend.dto.QueryRequest;
import com.query.backend.service.TicketService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/query")
@CrossOrigin("*")
public class QueryController {

    private final TicketService ticketService;

    public QueryController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Map<String, String> handleQuery(@RequestBody QueryRequest request) {
        
        String ticketId = ticketService.createTicket(request);

        return Map.of(
            "message", "Ticket created successfully",
            "ticketId", ticketId
        );
    }
}
