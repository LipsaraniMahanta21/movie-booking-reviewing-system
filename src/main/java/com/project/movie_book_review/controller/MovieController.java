package com.project.movie_book_review.controller;

import com.project.movie_book_review.entity.Genre;
import com.project.movie_book_review.dto.MovieResponse;
import com.project.movie_book_review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/{title}")
    public MovieResponse getMovie(@PathVariable String title){
        return movieService.findMovie(title);
    }

    @GetMapping("/{genre}")
    public List<MovieResponse> getMoviesByGenre(@PathVariable Genre genre){
        return movieService.getMoviesByGenre(genre);
    }
}
