package com.mm.libraryrestapi.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowHistoryDto {
    private long id;

    @NotNull(message = "Book user id should not be null")
    private Long userId;

    @NotNull(message = "Book id should not be null")
    private Long paperBookId;

}
