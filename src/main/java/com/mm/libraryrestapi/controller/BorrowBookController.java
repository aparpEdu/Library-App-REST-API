package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;
import com.mm.libraryrestapi.services.BorrowBookService;
import com.mm.libraryrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "CRUD REST APIs for Borrow Book Resource")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;

    public BorrowBookController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @Operation(
            summary = "Borrow Book REST API",
            description = "Borrow Book REST API is used to borrow a particular book from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("borrow/{bookId}")
    public ResponseEntity<BorrowHistoryDto> borrowBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(borrowBookService.borrowBook(bookId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Borrow Book With Postpone Days REST API",
            description = "Borrow Book With Postpone Days REST API is used to postpone the days of a borrowed book in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/postpone/{borrowId}")
    public ResponseEntity<BorrowHistoryDto> borrowBook(@PathVariable Long borrowId, @RequestParam("days") Long days) {
        return new ResponseEntity<>(borrowBookService.postponeReturnDate(borrowId, days), HttpStatus.OK);
    }

    @Operation(
            summary = "Return Book REST API",
            description = "Return Book REST API is used to return a particular book in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/return/{borrowId}")
    public ResponseEntity<BorrowHistoryDto> returnBook(@PathVariable Long borrowId) {
        return new ResponseEntity<>(borrowBookService.returnPaperBook(borrowId), HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Borrowed Books By User REST API",
            description = "Get All Borrowed Books By User REST API is used to fetch all borrowed books by a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
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

    @Operation(
            summary = "Get All Borrowed Books By Logged User REST API",
            description = "Get All Borrowed Books By Logged User REST API is used to fetch all borrowed books by a logged user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
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

    @Operation(
            summary = "Get All Borrowed Books REST API",
            description = "Get All Borrowed Books REST API is used to fetch all borrowed books from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("borrowHistory")
    public ResponseEntity<BorrowHistoryResponse> getAllBooksBorrowed
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(borrowBookService.getAllBooksBorrowed(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get Borrow History By User Id REST API",
            description = "Search Borrow History By User Id REST API is used to search borrow history by user id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("user")
    public ResponseEntity<BorrowHistoryResponse> getBorrowHistoryByUserId
            (
            @RequestParam Long userId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(borrowBookService.getBorrowHistoryByUserId(userId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get Borrow History By Book Id REST API",
            description = "Search Borrow History By Book Id REST API is used to search borrow history by book id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("book")
    public ResponseEntity<BorrowHistoryResponse> getBorrowHistoryByBookId
            (
                    @RequestParam Long bookId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(borrowBookService.getBorrowHistoryByBookId(bookId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get Borrow History By Borrow Date REST API",
            description = "Search Borrow History By Borrow Date REST API is used to search borrow history by borrow date from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("date")
    public ResponseEntity<List<BorrowHistory>> getBorrowHistoryByBorrowDate(@RequestParam LocalDate borrowDate) {
        return ResponseEntity.ok(borrowBookService.getBorrowHistoryByBorrowDate(borrowDate));
    }

    @Operation(
            summary = "Get Borrow History By Returned REST API",
            description = "Search Borrow History By Returned REST API is used to search borrow history if the book is returned or not from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("returned")
    public ResponseEntity<List<BorrowHistory>> getBorrowHistoryByReturned(@RequestParam boolean returned) {
        return ResponseEntity.ok(borrowBookService.getBorrowHistoryByReturned(returned));
    }
}
