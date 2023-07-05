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

    @NotNull(message = "Book user should not be null")
    private Long userId;

    @NotNull(message = "Paper Book cannot be null")
    private Long paperBookId;

}
