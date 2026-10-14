package com.project.movie_book_review.repository;

import com.project.movie_book_review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query(value = "select avg(rating) from review_table where movie_id=?",nativeQuery = true)
    Double getReviewAverage(Integer id);

    Optional<Review> findAllByMovieTitle(String movieTitle);
}
