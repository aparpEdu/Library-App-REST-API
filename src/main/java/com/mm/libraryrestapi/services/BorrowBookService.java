package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;

public interface BorrowBookService {
    BorrowHistoryDto borrowBook(BorrowHistoryDto borrowHistoryDto);
}
