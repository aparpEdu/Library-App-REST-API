package com.mm.libraryrestapi.payload.dtos;

import com.mm.libraryrestapi.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserCloudHistoryDto {
    private Book book;
    private Long userId;
    private LocalDateTime readTime;
    private LocalDateTime downloadTime;
}
