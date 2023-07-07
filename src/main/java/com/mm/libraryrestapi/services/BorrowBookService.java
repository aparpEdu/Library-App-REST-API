package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.dtos.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.response.BorrowHistoryResponse;

import java.time.LocalDate;

public interface BorrowBookService {
    BorrowHistoryDto borrowBook(Long bookId);

    BorrowHistoryDto postponeReturnDate(Long borrowHistoryId, Long days);

    BorrowHistoryDto returnPaperBook(Long borrowHistoryId);

    BorrowHistoryResponse getAllBooksBorrowedByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getAllBooksBorrowedByLoggedUser(int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getAllBooksBorrowed(int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse  getBorrowHistoryByUserId(Long userId,  int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getBorrowHistoryByBookId(Long bookId, int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getBorrowHistoryByBorrowDate(LocalDate borrowDate, int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getBorrowHistoryByReturned(boolean returned, int pageNo, int pageSize, String sortBy, String sortDir);
}
