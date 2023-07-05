package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.services.BorrowBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;
    private final BorrowHistoryRepository borrowHistoryRepository;

    public BorrowBookController(BorrowBookService borrowBookService, BorrowHistoryRepository borrowHistoryRepository) {
        this.borrowBookService = borrowBookService;
        this.borrowHistoryRepository = borrowHistoryRepository;
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(borrowBookService.borrowBook(bookId), HttpStatus.CREATED);
    }

    @PatchMapping("/{borrowId}/postpone")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long borrowId, @RequestParam("days") Long days) {
        return new ResponseEntity<>(borrowBookService.postponeReturnDate(borrowId, days), HttpStatus.CREATED);
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
