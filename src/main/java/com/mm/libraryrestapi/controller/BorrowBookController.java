package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.services.BorrowBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;

    public BorrowBookController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("borrow/{bookId}")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(borrowBookService.borrowBook(bookId), HttpStatus.CREATED);
    }
    @PatchMapping("/{borrowId}/postpone")
    public ResponseEntity<BorrowHistoryDto> borrowPaperBook(@PathVariable Long borrowId, @RequestParam("days") Long days) {
        return new ResponseEntity<>(borrowBookService.postponeReturnDate(borrowId, days), HttpStatus.OK);
    }
    @PatchMapping("/{borrowId}/return")
    public ResponseEntity<BorrowHistoryDto> returnPaperBook(@PathVariable Long borrowId) {
        return new ResponseEntity<>(borrowBookService.returnPaperBook(borrowId), HttpStatus.OK);
    }


}
