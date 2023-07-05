package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.services.BorrowBookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;

    public BorrowBookController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("{bookId}")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long bookId, @Valid @RequestBody BorrowHistoryDto borrowHistoryDto) {
        return new ResponseEntity<>(borrowBookService.borrowBook(bookId, borrowHistoryDto), HttpStatus.CREATED);
    }
}
