package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BorrowHistoryResponse extends GeneralResponse {
    private List<BorrowHistoryDto> content;

}
