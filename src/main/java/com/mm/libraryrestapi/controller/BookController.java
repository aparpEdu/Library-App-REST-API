package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.PaperBookResponse;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.services.BookService;
import com.mm.libraryrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "CRUD REST APIs for Book Resource")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @Operation(
            summary = "Get Book REST API",
            description = "Get Book REST API is used to get a particular book from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @Operation(
            summary = "Create Book REST API",
            description = "Create Book REST API is used to save book into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Books REST API",
            description = "Get All Books REST API is used to get all books from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<PaperBookResponse> getAllBooks
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update Book REST API",
            description = "Update Book REST API is used to update a particular book in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{bookId}")
    public ResponseEntity<BookDto> updateBookById(@Valid @RequestBody BookDto bookDto, @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.updateBookById(bookDto, bookId));
    }

    @Operation(
            summary = "Delete Book REST API",
            description = "Delete Book REST API is used to delete a particular book from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book was successfully deleted");
    }

    @Operation(
            summary = "Get Book By Title REST API",
            description = "Search Book By Title REST API is used to search a particular book by title from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("title")
    public ResponseEntity<Book> getBookByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookRepository.findByTitle(title));
    }

    @Operation(
            summary = "Get Book By Tags Name REST API",
            description = "Search Book By Tags REST API is used to search books by tags from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("tags")
    public ResponseEntity<List<Book>> getBookByTags(@RequestParam String tags) {
        return ResponseEntity.ok(bookRepository.findByTagsContaining(tags));
    }

    @Operation(
            summary = "Get Book By Summary REST API",
            description = "Search Book By Summary REST API is used to search books by summary from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("summary")
    public ResponseEntity<List<Book>> getBookBySummary(@RequestParam String summary) {
        return ResponseEntity.ok(bookRepository.findBySummaryContaining(summary));
    }

    @Operation(
            summary = "Get Book By Genre REST API",
            description = "Search Book By Genre REST API is used to search books by genre from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("genre")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(bookRepository.findByGenre(genre));
    }

    @Operation(
            summary = "Get Book By Publication Year REST API",
            description = "Search Book By Publication Year REST API is used to search books by publication year from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("year")
    public ResponseEntity<List<Book>> getBookByPublicationYear(@RequestParam int publicationYear) {
        return ResponseEntity.ok(bookRepository.findByPublicationYear(publicationYear));
    }

    @Operation(
            summary = "Get Book By Author Id REST API",
            description = "Search Book By Author Id REST API is used to search books by author id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("author")
    public ResponseEntity<List<Book>> getBookByAuthorId(@RequestParam int authorId) {
        return ResponseEntity.ok(bookRepository.findByAuthorId(authorId));
    }
}

