package com.project.movie_book_review.dto;

import com.project.movie_book_review.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class UserRequest {

    private long id;

    @NotBlank(message = "User name is Mandatory")
    private String name;

    @NotBlank(message = "password is Mandatory")
    private String password;


    private Role role;

    @NotBlank(message = "Mobile is Mandatory")
    private String mobile;

    @NotBlank(message = "Email is Mandatory")
    private String email;

    private List<TicketResource> tickets;
}
