package com.mm.libraryrestapi.payload.dtos;

import com.mm.libraryrestapi.entity.Ebook;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCloudHistoryDto {
    private Ebook ebook;
    private Long userId;
}
