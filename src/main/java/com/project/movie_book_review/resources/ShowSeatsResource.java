package com.project.movie_book_review.resources;

import com.project.movie_book_review.entity.SeatType;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeatsResource{

        private long id;

        private String seatNumber;

        private int rate;

        private SeatType seatType;

        private boolean booked;

        private Date bookedAt;
}
