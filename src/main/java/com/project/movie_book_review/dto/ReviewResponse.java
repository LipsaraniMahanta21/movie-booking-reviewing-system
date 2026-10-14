package com.project.movie_book_review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private String review;
    private Double rating;
}
