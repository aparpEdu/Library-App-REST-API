package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.PaperBook;
import com.mm.libraryrestapi.entity.User;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Book user should not be null or empty")
    private User user;

    @NotEmpty(message = "Book should not be null or empty")
    private PaperBook paperBook;

    @NotEmpty(message = "Book borrow date should not be null or empty")
    private LocalDate borrowDate;

    @NotEmpty(message = "Book return date should not be null or empty")
    private LocalDate returnDate;

    @NotNull(message = "Book should be returned or not")
    private Boolean returned;
}
