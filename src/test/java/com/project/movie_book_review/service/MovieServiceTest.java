package com.project.movie_book_review.service;

import com.project.movie_book_review.dto.MovieResponse;
import com.project.movie_book_review.entity.Movie;
import com.project.movie_book_review.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @InjectMocks
    MovieService movieService;
    @Mock
    MovieRepository movieRepository;
    @Test
    public void findMovieShouldFindAMovieByTitle(){//passed
        //data preparation
        Movie movie = new Movie();
        movie.setTitle("title");
        //mocking
        Mockito.when(movieRepository.findAllMovieByTitle(movie.getTitle())).thenReturn(Optional.of(movie));
        //calling actual methods
        MovieResponse testMovie=movieService.findMovie(movie.getTitle());
        //assertion
        Assertions.assertEquals(movie.getTitle(),testMovie.getTitle());
        Assertions.assertNotNull(testMovie);
    }
}
