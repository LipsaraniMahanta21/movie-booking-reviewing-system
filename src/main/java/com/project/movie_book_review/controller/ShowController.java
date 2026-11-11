package com.project.movie_book_review.controller;

import com.project.movie_book_review.resources.ShowResource;
import com.project.movie_book_review.service.ShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping("/show")
    public ResponseEntity<List<ShowResource>> getShow(@RequestParam(name="movieName",required = false)String movieName,
                                                      @RequestParam(name="cityName")String cityName,
                                                      @RequestParam(name="theaterName",required = false)String theaterName) {
            return ResponseEntity.ok(showService.searchShows(movieName,cityName,theaterName));
    }


}
