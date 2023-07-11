package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.dtos.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.response.BorrowHistoryResponse;

import java.time.LocalDate;

public interface BorrowHistoryService {
    BorrowHistoryDto borrowBookById(Long userId, Long bookId);

    BorrowHistoryDto postponeBookByHistoryId(Long userId, Long borrowHistoryId, Long days);

    BorrowHistoryDto returnBookByHistoryId(Long userId, Long borrowHistoryId);

    BorrowHistoryResponse getAllBooksBorrowedByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

//    BorrowHistoryResponse getAllBooksBorrowedByLoggedUser(int pageNo, int pageSize, String sortBy, String sortDir);

//    BorrowHistoryResponse getAllBooksBorrowed(int pageNo, int pageSize, String sortBy, String sortDir);

//    BorrowHistoryResponse  getBorrowHistoryByUserId(Long userId,  int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getBorrowHistoryByBookId(Long userId, Long bookId, int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getBorrowHistoryByBorrowDate(Long userId, LocalDate borrowDate, int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getBorrowHistoryByReturned(Long userId, boolean returned, int pageNo, int pageSize, String sortBy, String sortDir);
}
