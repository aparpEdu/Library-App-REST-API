package com.mm.libraryrestapi.controller;


import com.mm.libraryrestapi.payload.dtos.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.response.UserCloudHistoryResponse;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "CRUD REST APIs for User Cloud History Resource")
public class UserCloudHistoryController {
    private final UserCloudHistoryService userCloudHistoryService;

    public UserCloudHistoryController(UserCloudHistoryService userCloudHistoryService) {
        this.userCloudHistoryService = userCloudHistoryService;
    }

    @Operation(
            summary = "Read A Book REST API",
            description = "Read A Book REST API is used to read a book from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("{userId}/read/{bookId}")
    public ResponseEntity<UserCloudHistoryDto> readABook(@PathVariable Long userId, @PathVariable Long bookId) {
        return new ResponseEntity<>(userCloudHistoryService.readABook(bookId, userId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Download A Book REST API",
            description = "Download A Book REST API is used to download a book from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("{userId}/download/{bookId}")
    public ResponseEntity<UserCloudHistoryDto> downloadABook(@PathVariable Long userId, @PathVariable Long bookId) {
        return new ResponseEntity<>(userCloudHistoryService.downloadABook(bookId, userId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User's Read Or Downloaded Book By Book Id REST API",
            description = "Search User's Read Or Downloaded Book By Book Id REST API is used to search a user's book by book id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/mybooks/{bookId}")
    public ResponseEntity<UserCloudHistoryDto> getUserBookByBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.ok(userCloudHistoryService.getBookByBookId(bookId, userId));
    }

    @Operation(
            summary = "Get All User's Books REST API",
            description = "Search All User's Books REST API is used to get all books by a user's id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/mybooks")
    public ResponseEntity<UserCloudHistoryResponse> getAllBooksByUserId
            (@PathVariable Long userId,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(userCloudHistoryService.getAllBooksByUserId(userId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get User's History By Read Time REST API",
            description = "Search User's History By Read Time REST API is used to get user's all read books by read time from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/mybooks/read")
    public ResponseEntity<UserCloudHistoryResponse> getCloudHistoryByReadTime
            (@PathVariable Long userId,
             @RequestParam LocalDateTime readTime,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(userCloudHistoryService.getCloudHistoryByReadTime(userId, readTime, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get User's History By Download Time REST API",
            description = "Get User's History By Download Time REST API is used to get user's all read books by download time from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/mybooks/downloaded")
    public ResponseEntity<UserCloudHistoryResponse> getCloudHistoryByDownloadTime
            (@PathVariable Long userId,
             @RequestParam LocalDateTime downloadTime,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(userCloudHistoryService.getCloudHistoryByDownloadTime(userId, downloadTime, pageNo, pageSize, sortBy, sortDir));
    }
}
