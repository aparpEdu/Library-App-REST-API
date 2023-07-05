//package com.mm.libraryrestapi.controller;
//
//import com.mm.libraryrestapi.entity.Book;
//import com.mm.libraryrestapi.payload.BookDto;
//import com.mm.libraryrestapi.services.BookService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/books")
//public class BookController {
//
//    private final BookService bookService;
//
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }
//    @PostMapping("")
//    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
//        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
//    }
//
//    }
//
