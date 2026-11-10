package com.project.movie_book_review.repository;

import com.project.movie_book_review.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
