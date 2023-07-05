package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCloudHistoryDto {
    private EbookDto ebookDto;
    private Long userId;
}
