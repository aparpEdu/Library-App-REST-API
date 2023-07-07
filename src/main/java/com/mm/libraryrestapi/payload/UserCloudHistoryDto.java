package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Ebook;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCloudHistoryDto {
    @NotNull(message = "Ebook should not be null")
    private Ebook ebook;
    @NotNull(message = "UserId should not be null")
    private Long userId;
}
