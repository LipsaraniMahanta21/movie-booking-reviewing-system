package com.project.movie_book_review.repository;

import com.project.movie_book_review.entity.Genre;
import com.project.movie_book_review.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findMovieByTitle(String title);
    List<Movie> findAllByGenre(Genre genre);
}
