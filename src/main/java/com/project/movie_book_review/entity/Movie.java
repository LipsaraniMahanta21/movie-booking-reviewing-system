package com.project.movie_book_review.entity;

import com.project.movie_book_review.dto.MovieResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Movie implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Double rating;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @CreationTimestamp
    public Date createdAt;
    @UpdateTimestamp
    public Date updatedAt;

    public MovieResponse toMovieResponse(){
        return MovieResponse.builder()
                .genre(this.genre)
                .title(this.title)
                .rating(this.rating)
                .reviews(Review.toReviewResponseList(this.reviews))
                .build();
    }
}
