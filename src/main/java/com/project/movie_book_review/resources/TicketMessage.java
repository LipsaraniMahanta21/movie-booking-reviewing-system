package com.project.movie_book_review.resources;

import com.project.movie_book_review.entity.Show;
import com.project.movie_book_review.entity.ShowSeat;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
public class TicketMessage {

    private String  userName;
    private String  mobile;
    private String email;
    private Show show;
    private List<ShowSeat> seats;
}
