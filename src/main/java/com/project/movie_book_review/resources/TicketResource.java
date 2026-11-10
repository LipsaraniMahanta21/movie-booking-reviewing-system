package com.project.movie_book_review.resources;

import lombok.*;
import com.project.movie_book_review.resources.ShowResource;
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
