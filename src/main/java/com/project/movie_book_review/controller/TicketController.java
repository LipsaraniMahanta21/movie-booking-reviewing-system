package com.project.movie_book_review.controller;

import com.project.movie_book_review.resources.BookingResource;
import com.project.movie_book_review.resources.TicketResource;
import com.project.movie_book_review.service.TicketService;

import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    Logger log;

    @PostMapping("/book")
    public ResponseEntity<TicketResource> bookTicket(@RequestBody BookingResource bookingResource){
        log.info("Received request to book ticket:"+bookingResource);
        return ResponseEntity.ok(ticketService.bookTicket(bookingResource));
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketResource> getTicket(@PathVariable(name="id")@Min(value=1,message="Minimum value is 1")Integer id){
        log.info("Received Request to get ticket: "+id);
        return ResponseEntity.ok(ticketService.getTicket(id));
    }
}
