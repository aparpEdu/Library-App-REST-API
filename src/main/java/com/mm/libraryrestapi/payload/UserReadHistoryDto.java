package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReadHistoryDto {
    private Long ebookId;
    private Long userId;
}
