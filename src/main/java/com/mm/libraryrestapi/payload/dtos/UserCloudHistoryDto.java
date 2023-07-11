package com.mm.libraryrestapi.payload.dtos;

import com.mm.libraryrestapi.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCloudHistoryDto {
    private Book book;
    private Long userId;
}
