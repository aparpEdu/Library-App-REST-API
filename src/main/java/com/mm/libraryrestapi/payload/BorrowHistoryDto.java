package com.mm.libraryrestapi.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowHistoryDto {
    private long id;

    @NotNull(message = "Book user id should not be null")
    private Long userId;

    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}
