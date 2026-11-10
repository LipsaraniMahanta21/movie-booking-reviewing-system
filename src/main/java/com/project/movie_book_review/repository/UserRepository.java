package com.project.movie_book_review.repository;

import com.project.movie_book_review.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
