package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;

public interface BorrowBookService {
    BorrowHistoryDto borrowBook( Long bookId);
    BorrowHistoryDto postponeReturnDate( Long borrowHistoryId, Long days);
    BorrowHistoryDto returnPaperBook( Long borrowHistoryId);

    BorrowHistoryResponse getAllBooksBorrowedByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);
    BorrowHistoryResponse getAllBooksBorrowedByLoggedUser(int pageNo, int pageSize, String sortBy, String sortDir);
    BorrowHistoryResponse getAllBooksBorrowed(int pageNo, int pageSize, String sortBy, String sortDir);
}
