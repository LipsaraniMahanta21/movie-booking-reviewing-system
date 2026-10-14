package com.project.movie_book_review.dto;

import com.project.movie_book_review.entity.Movie;
import com.project.movie_book_review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private String movieReview;
    private double rating;
    private int movieId;

    public Review toReview() {
        return Review.builder()
                .movieReview(movieReview)
                .rating(rating)
                .movie(Movie.builder().id(movieId).build())//we only need an id
                .build();
    }
}
