package com.project.movie_book_review.service;

import com.project.movie_book_review.dto.MovieResponse;
import com.project.movie_book_review.entity.Genre;
import com.project.movie_book_review.entity.Movie;
import com.project.movie_book_review.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public MovieResponse findMovie(String title) {
        Movie movie= movieRepository.findMovieByTitle(title).orElse(null);
        if(Objects.nonNull(movie)){
            return movie.toMovieResponse();
        }
        return null;
    }

    public List<MovieResponse> getMoviesByGenre(Genre genre) {
        if(Arrays.stream(Genre.values()).noneMatch(g -> g.equals(genre))) {
            return new ArrayList<>();
        }
        List<Movie> movieList = movieRepository.findAllByGenre(genre);
        if(!CollectionUtils.isEmpty(movieList)) {
            List<MovieResponse> movieResponseList = movieList.stream().sorted(Comparator.comparing(Movie::getRating, Comparator.reverseOrder()))
                    .map(m -> m.toMovieResponse()).toList();
            if (movieResponseList.size() > 5) {
                return movieResponseList.subList(0, 4);
            }
            return movieResponseList;
        }
        return new ArrayList<>();
    }
}
