package com.project.movie_book_review.service;

import com.project.movie_book_review.dto.TicketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    ObjectMapper mapper=new ObjectMapper();

    public void sendNotification(TicketMessage ticketMessage) {
        try {
            sendEmailToUser(ticketMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendEmailToUser(TicketMessage message) throws JacksonException {
        log.info("calling email service for showDetails: {}  seatDetails : {}to number {}", message.getShow(), message.getSeats(), message.getEmail());

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(message.getEmail());
        mailMessage.setSubject("MovieShark Tickets");
        mailMessage.setText("Show: "+message.getShow()+" Tickets: "+message.getSeats());

        log.info(mapper.writeValueAsString(mailMessage));

        javaMailSender.send(mailMessage);
    }
}
