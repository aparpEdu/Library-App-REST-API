package com.mm.libraryrestapi.payload.response;

import com.mm.libraryrestapi.payload.dtos.BorrowHistoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BorrowHistoryResponse extends GeneralResponse {
    private List<BorrowHistoryDto> content;

}
