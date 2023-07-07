package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;

import java.time.LocalDate;
import java.util.List;

public interface BorrowBookService {
    BorrowHistoryDto borrowBook(Long bookId);

    BorrowHistoryDto postponeReturnDate(Long borrowHistoryId, Long days);

    BorrowHistoryDto returnPaperBook(Long borrowHistoryId);

    BorrowHistoryResponse getAllBooksBorrowedByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getAllBooksBorrowedByLoggedUser(int pageNo, int pageSize, String sortBy, String sortDir);

    BorrowHistoryResponse getAllBooksBorrowed(int pageNo, int pageSize, String sortBy, String sortDir);

    List<BorrowHistory> getBorrowHistoryByUserId(Long userId);

    List<BorrowHistory> getBorrowHistoryByBookId(Long bookId);

    List<BorrowHistory> getBorrowHistoryByBorrowDate(LocalDate borrowDate);

    List<BorrowHistory> getBorrowHistoryByReturned(boolean returned);
}
