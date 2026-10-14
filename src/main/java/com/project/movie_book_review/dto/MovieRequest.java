package com.project.movie_book_review.dto;

import com.project.movie_book_review.entity.Genre;
import com.project.movie_book_review.entity.Movie;
import com.project.movie_book_review.entity.Review;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private String title;
    private Genre genre;

    public Movie toMovie() {
        return Movie.builder()
                .title(title)
                .genre(genre)
                .rating(0.0)
                .build();
    }
}
