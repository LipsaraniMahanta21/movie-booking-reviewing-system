package com.project.movie_book_review.controller;

import com.project.movie_book_review.entity.Review;
import com.project.movie_book_review.dto.ReviewRequest;
import com.project.movie_book_review.dto.ReviewResponse;
import com.project.movie_book_review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/{movieTitle}")
    public Review addReview(@PathVariable String movieTitle, @RequestBody ReviewRequest reviewRequest){
          return reviewService.addReview(reviewRequest.toReview());
    }

    @GetMapping("/find")
    public ReviewResponse findReviews(@RequestParam String movieTitle){
        return reviewService.getReviewByMovieTitle(movieTitle);
    }

}
