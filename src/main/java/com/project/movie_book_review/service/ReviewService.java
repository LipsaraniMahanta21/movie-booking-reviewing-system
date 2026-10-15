package com.project.movie_book_review.service;

import com.project.movie_book_review.dto.ReviewResponse;
import com.project.movie_book_review.entity.Movie;
import com.project.movie_book_review.entity.Review;
import com.project.movie_book_review.repository.MovieRepository;
import com.project.movie_book_review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MovieRepository movieRepository;

    public Review addReview(Review review){
        Movie movie=movieRepository.findMovieByTitle(review.getMovie().getTitle()).orElse(null);
        reviewRepository.save(review);
        if(movie!=null){
            Double average=reviewRepository.getReviewAverage(movie.getId());
            movie.setRating(average);
            movieRepository.save(movie);
        }
        return reviewRepository.save(review);
    }

    public ReviewResponse getReviewByMovieTitle(String movieTitle) {
        Optional<Review> review=reviewRepository.findAllByMovieTitle(movieTitle);
        return review.map(Review::toReviewResponse).orElse(null);
    }
}
