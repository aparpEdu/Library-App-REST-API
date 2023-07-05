package com.mm.libraryrestapi.controller;


import com.mm.libraryrestapi.entity.UserCloudHistory;
import com.mm.libraryrestapi.payload.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.UserCloudHistoryResponse;
import com.mm.libraryrestapi.repositories.UserCloudHistoryRepository;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "CRUD REST APIs for User Clod History Resource")
public class UserCloudHistoryController {
    private final UserCloudHistoryService userCloudHistoryService;
    private final UserCloudHistoryRepository userCloudHistoryRepository;

    public UserCloudHistoryController(UserCloudHistoryService userCloudHistoryService, UserCloudHistoryRepository userCloudHistoryRepository) {
        this.userCloudHistoryService = userCloudHistoryService;
        this.userCloudHistoryRepository = userCloudHistoryRepository;
    }

    @Operation(
            summary = "Read An EBook REST API",
            description = "Read An EBook REST API is used to read an ebook from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("{userId}/read/{ebookId}")
    public ResponseEntity<UserCloudHistoryDto> readABook(@PathVariable Long userId, @PathVariable Long ebookId) {
        return new ResponseEntity<>(userCloudHistoryService.readABook(ebookId, userId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Read An EBook By User REST API",
            description = "Read An EBook By User REST API is used to read an ebook by user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/ebooks/{ebookId}")
    public ResponseEntity<UserCloudHistoryDto> getReadBookByUser(@PathVariable Long userId, @PathVariable Long ebookId) {
        return ResponseEntity.ok(userCloudHistoryService.getUserReadBook(ebookId, userId));
    }

    @Operation(
            summary = "Get Read EBook By User REST API",
            description = "Get Read EBook By User REST API is used to get all read ebooks by a user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/ebooks")
    public ResponseEntity<UserCloudHistoryResponse> getReadBookByUser
            (@PathVariable Long userId,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(userCloudHistoryService.getAllReadBooksByUser(userId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get User Cloud History By User Id REST API",
            description = "Search User Cloud History By User Id API is used to search for cloud history by user id in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("user")
    public ResponseEntity<List<UserCloudHistory>> getUserCloudHistoryByUserId(Long userId) {
        return ResponseEntity.ok(userCloudHistoryRepository.findByUserId(userId));
    }

    @Operation(
            summary = "Get User Cloud History By EBook Id REST API",
            description = "Search User Cloud History By EBook Id REST API is used to search for cloud history by ebook id in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("ebook")
    public ResponseEntity<List<UserCloudHistory>> getUserCloudHistoryByEbookId(Long ebookId) {
        return ResponseEntity.ok(userCloudHistoryRepository.findByEbookId(ebookId));
    }
}
