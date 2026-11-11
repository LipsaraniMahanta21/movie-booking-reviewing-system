package com.project.movie_book_review.service;

import com.project.movie_book_review.entity.Show;
import com.project.movie_book_review.resources.ShowResource;
import com.project.movie_book_review.repository.MovieRepository;
import com.project.movie_book_review.repository.ShowRepository;
import com.project.movie_book_review.repository.TheaterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public List<ShowResource> searchShows(String movieName, String cityName,String theaterName) {
        if(!StringUtils.hasText(cityName)){
            new ArrayList<>();
        }
        List<Show> shows;
        if(StringUtils.hasText(movieName)){
            shows=showRepository.findAllByMovie_TitleAndTheater_City(movieName,cityName);
        }else if(!StringUtils.hasText(theaterName)){
            shows=showRepository.findAllByTheaterAndCity(theaterName,cityName);
        } else{
            shows=showRepository.findByCity(cityName);
        }

        if(CollectionUtils.isEmpty(shows)){
            return new ArrayList<>();
        }else{
            return shows.stream()
                    .map(Show::toResource)
                    .collect(Collectors.toList());
        }
    }
}
