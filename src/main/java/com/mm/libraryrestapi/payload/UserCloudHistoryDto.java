package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Ebook;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCloudHistoryDto {
    private Ebook ebook;
    private Long userId;
}
