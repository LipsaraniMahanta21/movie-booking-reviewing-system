package com.project.movie_book_review.dto;

import com.project.movie_book_review.entity.Genre;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {
    private Genre genre;
    private String title;
    private Double rating;
    private List<ReviewResponse> reviews;

}
