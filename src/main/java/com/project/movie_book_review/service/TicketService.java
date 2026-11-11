package com.project.movie_book_review.service;

import com.project.movie_book_review.Exception.NotFoundException;

import com.project.movie_book_review.entity.ShowSeat;
import com.project.movie_book_review.entity.Ticket;
import com.project.movie_book_review.entity.Show;
import com.project.movie_book_review.entity.User;

import com.project.movie_book_review.repository.ShowRepository;
import com.project.movie_book_review.repository.TicketRepository;
import com.project.movie_book_review.repository.UserRepository;
import com.project.movie_book_review.resources.BookingResource;
import com.project.movie_book_review.resources.TicketMessage;
import com.project.movie_book_review.resources.TicketResource;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.kafka.core.KafkaTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private static String topic="TICKET BOOKED";
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Logger log;

    @Autowired
    TicketRepository ticketRepository;
    public TicketResource bookTicket(BookingResource bookingResource){
        Optional<User> optionalUser=userRepository.findById(bookingResource.getUserId());
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found with ID: "+bookingResource.getUserId());
        }

        Optional<Show> optionalShow=showRepository.findById(bookingResource.getShowId());
        if(optionalShow.isEmpty()){
            throw new NotFoundException("Show not found with ID: "+bookingResource.getShowId());
        }
        //logic to check whether seats are available or not
        Set<String> requestedSeats=bookingResource.getSeatNumbers();
        List<ShowSeat> showSeatList=optionalShow.get().getSeats();

        showSeatList=showSeatList.stream()
                .filter(seat->seat.getSeatType().equals(bookingResource.getSeatType())
                        && !seat.isBooked()
                        && requestedSeats.contains(seat.getSeatNumber()))
                .collect(Collectors.toList());

        if(showSeatList.size() != requestedSeats.size()){
            throw new NotFoundException("Seats not found");
        }
        Ticket ticket=Ticket.builder()
                .user(optionalUser.get())
                .show(optionalShow.get())
                .seats(showSeatList)
                .build();

        double amount=0.0;
        String allotedSeats="";

        for(ShowSeat seats:showSeatList){
            seats.setBooked(true);
            seats.setBookedAt(new Date());
            seats.setTicket(ticket);

            amount += seats.getRate();
            allotedSeats += seats.getSeatNumber();
        }

        ticket.setAmount(amount);
        ticket.setAllottedSeats(allotedSeats);

        if(CollectionUtils.isEmpty(optionalUser.get().getTickets())){
            optionalUser.get().setTickets(new ArrayList<>());
        }
        optionalUser.get().getTickets().add(ticket);

        if(CollectionUtils.isEmpty(optionalShow.get().getTickets())){
            optionalShow.get().setTickets(new ArrayList<>());
        }
        optionalShow.get().getTickets().add(ticket);

        ticket=ticketRepository.save(ticket);

        try{//sending message through kafka
            TicketMessage message=new TicketMessage(ticket.getUser().getName(),ticket.getUser().getMobile(),ticket.getUser().getEmail(), ticket.getShow(), ticket.getSeats());
            log.info("sending kafka message on booking {}", objectMapper.writeValueAsString(message));
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        }catch(Exception e){
            log.error("Exception while sending notification service");
        }

        return Ticket.toResource(ticket);
    }

    public TicketResource getTicket(long id) {
        Optional<Ticket> ticketEntity = ticketRepository.findById(id);

        if (ticketEntity.isEmpty()) {
            log.error("Ticket not found for id: " + id);
            throw new EntityNotFoundException("Ticket Not Found with ID: " + id);
        }

        return Ticket.toResource(ticketEntity.get());
    }

}
