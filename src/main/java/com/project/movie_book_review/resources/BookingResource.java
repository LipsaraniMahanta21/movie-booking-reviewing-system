package com.project.movie_book_review.resources;

import com.project.movie_book_review.entity.SeatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookingResource {

    @NotEmpty(message="SeatNumbers cannot be empty")
    private Set<String> seatNumbers;

    @Min(value=1,message="Invalid user ID")
    private int userId;

    @Min(value = 1, message = "Invalid show ID")
    private int showId;

    @NotNull(message = "seatType cannot be null")
    private SeatType seatType;
}
