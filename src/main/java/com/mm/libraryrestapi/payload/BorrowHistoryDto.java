package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowHistoryDto {
    private long id;
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}
