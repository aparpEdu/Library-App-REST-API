package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.payload.*;
import com.mm.libraryrestapi.services.BookService;
import com.mm.libraryrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("{bookId}")
    public ResponseEntity<PaperBookDto> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping("")
    public ResponseEntity<PaperBookDto> createEbook(@Valid @RequestBody PaperBookDto paperBookDto) {
        return new ResponseEntity<>(bookService.createBook(paperBookDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<PaperBookResponse> getAllEbooks
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("{bookId}")
    public ResponseEntity<PaperBookDto> updateEbookById(@Valid @RequestBody PaperBookDto paperBookDto, @PathVariable Long bookId){
        return ResponseEntity.ok(bookService.updateBookById(paperBookDto, bookId));
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteEbookById(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book was successfully deleted");
    }
    }

