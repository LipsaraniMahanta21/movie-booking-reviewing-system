package com.project.movie_book_review.consumer;

import com.project.movie_book_review.dto.TicketMessage;
import com.project.movie_book_review.service.NotificationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;

public class NotificationConsumer {
    @Autowired
    NotificationService notificationService;
    @Autowired
    Logger log;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics={"TICKET-BOOKED"},groupId = "ticketGroup")
    public void sendNotification(String message) throws JacksonException {
        log.info("message -> {}", message);
        TicketMessage ticketMessage = objectMapper.readValue(message,TicketMessage.class);

        notificationService.sendNotification(ticketMessage);
    }
}
