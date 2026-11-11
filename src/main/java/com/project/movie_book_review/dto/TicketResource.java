package com.project.movie_book_review.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TicketResource {

    private Long id;

    private String allottedSeats;

    private double amount;

    private Date bookedAt;

    private ShowResource show;
}
