package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.services.BorrowBookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowHistoryController {

    private final BorrowBookService borrowBookService;

    public BorrowHistoryController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@Valid @RequestBody BorrowHistoryDto borrowHistoryDto) {
        return new ResponseEntity<>(borrowBookService.borrowBook(borrowHistoryDto), HttpStatus.CREATED);
    }
}
