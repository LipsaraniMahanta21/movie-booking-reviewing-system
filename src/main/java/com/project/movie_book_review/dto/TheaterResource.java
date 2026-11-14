package com.project.movie_book_review.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TheaterResource {

    private long id;
    private String name;
    private String city;
    private String address;
    private List<ShowResource> shows;

}
