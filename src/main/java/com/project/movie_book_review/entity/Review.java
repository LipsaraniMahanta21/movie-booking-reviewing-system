package com.project.movie_book_review.entity;

import com.project.movie_book_review.dto.ReviewResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String movieReview;
    private double rating;

    @ManyToMany
    @JoinColumn(name="movie_id",nullable = false)
//    @JsonIgnore
    private Movie movie;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public static ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .review(review.movieReview)
                .rating(review.rating)
                .build();
    }
    public static List<ReviewResponse> toReviewResponseList(List<Review> reviews) {
        if(Objects.isNull(reviews)){
            return null;
        }else{
            return reviews.stream().map(Review::toReviewResponse).toList();
        }

    }
}
