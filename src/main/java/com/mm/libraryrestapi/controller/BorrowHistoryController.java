package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.dtos.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.response.BorrowHistoryResponse;
import com.mm.libraryrestapi.services.BorrowHistoryService;
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

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "CRUD REST APIs for Borrow Book Resource")
public class BorrowHistoryController {

    private final BorrowHistoryService borrowHistoryService;

    public BorrowHistoryController(BorrowHistoryService borrowHistoryService) {
        this.borrowHistoryService = borrowHistoryService;
    }

    @Operation(
            summary = "Borrow Book REST API",
            description = "Borrow Book REST API is used to borrow a particular book from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("{userId}/borrow/{bookId}")
    public ResponseEntity<BorrowHistoryDto> borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return new ResponseEntity<>(borrowHistoryService.borrowBookById(userId, bookId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Borrow Book With Postpone Days REST API",
            description = "Borrow Book With Postpone Days REST API is used to postpone the days of a borrowed book in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{userId}/postpone/{borrowId}")
    public ResponseEntity<BorrowHistoryDto> postponeBook(@PathVariable Long userId, @PathVariable Long borrowId, @RequestParam("days") Long days) {
        return new ResponseEntity<>(borrowHistoryService.postponeBookByHistoryId(userId, borrowId, days), HttpStatus.OK);
    }

    @Operation(
            summary = "Return Book REST API",
            description = "Return Book REST API is used to return a particular book in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{userId}/return/{borrowId}")
    public ResponseEntity<BorrowHistoryDto> returnBook(@PathVariable Long userId, @PathVariable Long borrowId) {
        return new ResponseEntity<>(borrowHistoryService.returnBookByHistoryId(userId, borrowId), HttpStatus.OK);
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/myBorrowHistory")
    public ResponseEntity<BorrowHistoryResponse> getAllBooksBorrowedByUser
            (@PathVariable Long userId,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(borrowHistoryService.getAllBooksBorrowedByUser(userId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get Borrow History By Book Id REST API",
            description = "Search Borrow History By Book Id REST API is used to search borrow history by book id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/myBorrowHistory/{bookId}")
    public ResponseEntity<BorrowHistoryResponse> getBorrowHistoryByBookId
            (
                    @PathVariable Long userId,
                    @PathVariable Long bookId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(borrowHistoryService.getBorrowHistoryByBookId(userId, bookId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get Borrow History By Borrow Date REST API",
            description = "Search Borrow History By Borrow Date REST API is used to search borrow history by borrow date from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/myBorrowHistory/date")
    public ResponseEntity<BorrowHistoryResponse> getBorrowHistoryByBorrowDate(
            @PathVariable Long userId,
            @RequestParam(value = "date") LocalDate borrowDate,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(borrowHistoryService.getBorrowHistoryByBorrowDate(userId, borrowDate, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get Borrow History By Returned REST API",
            description = "Search Borrow History By Returned REST API is used to search borrow history if the book is returned or not from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/myBorrowHistory/returned")
    public ResponseEntity<BorrowHistoryResponse> getBorrowHistoryByReturned(
            @PathVariable Long userId,
            @RequestParam boolean returned,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(borrowHistoryService.getBorrowHistoryByReturned(userId, returned, pageNo, pageSize, sortBy, sortDir));
    }
}
