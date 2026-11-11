package com.project.movie_book_review.repository;

import com.project.movie_book_review.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    //Query Method
    List<Show> findAllByMovie_TitleAndTheater_City(String movieName, String cityName);

    //JPQL
    @Query(value="select s from Show s, Theater t where s.theater.id=t.id and t.city=?1")
    //where s.theatre.city= :cityName(@Param("cityName")String cityName);
    //s.theater.city=?1
    List<Show> findByCity(String cityName);

    //mysql
    @Query(value="select * from show s, theater t where s.theater_id=t.id and t.name=? and t.city=?",nativeQuery = true)
    List<Show> findAllByTheaterAndCity(String theaterName, String cityName);

}

