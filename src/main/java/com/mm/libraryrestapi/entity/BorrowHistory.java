package com.mm.libraryrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrow_history")
public class BorrowHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}
