package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;

public interface BorrowPaperBookService {
    BorrowHistoryDto borrowPaperBook(BorrowHistoryDto borrowHistoryDto);
}
