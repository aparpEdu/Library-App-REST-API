package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BorrowHistoryDto {
    private long id;
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}
