package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;

public interface BorrowBookService {
    BorrowHistoryDto borrowBook( Long bookId,BorrowHistoryDto borrowHistoryDto);
}
