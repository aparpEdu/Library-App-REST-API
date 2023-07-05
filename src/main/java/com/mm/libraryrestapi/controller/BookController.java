package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.PaperBookResponse;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.services.BookService;
import com.mm.libraryrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<PaperBookResponse> getAllBooks
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{bookId}")
    public ResponseEntity<BookDto> updateBookById(@Valid @RequestBody BookDto bookDto, @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.updateBookById(bookDto, bookId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book was successfully deleted");
    }

    @GetMapping("title")
    public ResponseEntity<Book> getBookByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookRepository.findByTitle(title));
    }

    @GetMapping("tags")
    public ResponseEntity<List<Book>> getBookByTags(@RequestParam String tags) {
        return ResponseEntity.ok(bookRepository.findByTagsContaining(tags));
    }

    @GetMapping("summary")
    public ResponseEntity<List<Book>> getBookBySummary(@RequestParam String summary) {
        return ResponseEntity.ok(bookRepository.findBySummaryContaining(summary));
    }

    @GetMapping("genre")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(bookRepository.findByGenre(genre));
    }

    @GetMapping("year")
    public ResponseEntity<List<Book>> getBookByPublicationYear(@RequestParam int publicationYear) {
        return ResponseEntity.ok(bookRepository.findByPublicationYear(publicationYear));
    }

    @GetMapping("author")
    public ResponseEntity<List<Book>> getBookByAuthorId(@RequestParam int authorId) {
        return ResponseEntity.ok(bookRepository.findByAuthorId(authorId));
    }
}

