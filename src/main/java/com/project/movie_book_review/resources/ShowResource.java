package com.project.movie_book_review.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.movie_book_review.entity.Time;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.jdk8.LongStreamSerializer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowResource {
    private int id;

//    @NonNull(message = "Show time is mandatory")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd'T'HH:mm:ss[.SSS][.SS][.s]")
    @JsonSerialize(using = LongStreamSerializer.class)
    @JsonDeserialize(using= LocalDateTimeDeserializer.class)
    private LocalDateTime showTime;
    private Time time;

//    @NonNull(message= "Movie is Mandatory for show")
    private int movieId;

    private int theaterId;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

//    private TheatreResource theatreResource;

    private List<ShowSeatsResource> seats;

}
