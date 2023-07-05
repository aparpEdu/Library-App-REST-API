package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.services.BorrowBookService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;
    private final BorrowHistoryRepository borrowHistoryRepository;

    public BorrowBookController(BorrowBookService borrowBookService, BorrowHistoryRepository borrowHistoryRepository) {
        this.borrowBookService = borrowBookService;
        this.borrowHistoryRepository = borrowHistoryRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("borrow/{bookId}")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(borrowBookService.borrowBook(bookId), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{borrowId}/postpone")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long borrowId, @RequestParam("days") Long days) {
        return new ResponseEntity<>(borrowBookService.postponeReturnDate(borrowId, days), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{borrowId}/return")
    public ResponseEntity<BorrowHistoryDto> returnPaperBook(@PathVariable Long borrowId) {
        return new ResponseEntity<>(borrowBookService.returnPaperBook(borrowId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("borrowHistory/{userId}")
    public ResponseEntity<BorrowHistoryResponse> getAllBooksBorrowedByUser
            (@PathVariable Long userId,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(borrowBookService.getAllBooksBorrowedByUser(userId, pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("myBorrowHistory")
    public ResponseEntity<BorrowHistoryResponse> getAllBooksBorrowedByLoggedUser
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(borrowBookService.getAllBooksBorrowedByLoggedUser(pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("borrowHistory")
    public ResponseEntity<BorrowHistoryResponse> getAllBooksBorrowed
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(borrowBookService.getAllBooksBorrowed(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("user")
    public ResponseEntity<List<BorrowHistory>> getBorrowHistoryByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(borrowHistoryRepository.findByUserId(userId));
    }

    @GetMapping("book")
    public ResponseEntity<List<BorrowHistory>> getBorrowHistoryByBookId(@RequestParam Long bookId) {
        return ResponseEntity.ok(borrowHistoryRepository.findByBookId(bookId));
    }

    @GetMapping("date")
    public ResponseEntity<List<BorrowHistory>> getBorrowHistoryByBorrowDate(@RequestParam LocalDate borrowDate) {
        return ResponseEntity.ok(borrowHistoryRepository.findByBorrowDate(borrowDate));
    }

    @GetMapping("returned")
    public ResponseEntity<List<BorrowHistory>> getBorrowHistoryByReturned(@RequestParam boolean returned) {
        return ResponseEntity.ok(borrowHistoryRepository.findByReturned(returned));
    }
}
