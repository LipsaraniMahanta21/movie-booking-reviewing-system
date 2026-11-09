package com.project.movie_book_review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.movie_book_review.resources.TheaterResource;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Theater {
    @Autowired
    TheaterResource theaterResource;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "city", nullable = false)
        private String city;

        @Column(name = "address", nullable = false)
        private String address;

        @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
        @JsonIgnore
        @Builder.Default
        private List<Show> shows = new ArrayList<>();

        @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
        @JsonIgnore
        @Builder.Default
        private List<TheaterSeats> seats = new ArrayList<>();

        public static Theater toEntity(TheaterResource theaterResource) {

            return Theater.builder()
                    .name(theaterResource.getName())
                    .city(theaterResource.getCity())
                    .address(theaterResource.getAddress())
                    .build();
        }

        public static TheaterResource toResource(Theater theater) {

            return TheaterResource.builder()
                    .id(theater.getId())
                    .name(theater.getName())
                    .city(theater.getCity())
                    .address(theater.getAddress())
                    .build();
        }
}
