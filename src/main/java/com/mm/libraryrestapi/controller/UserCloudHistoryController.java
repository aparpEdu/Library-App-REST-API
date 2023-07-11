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

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "CRUD REST APIs for User Cloud History Resource")
public class UserCloudHistoryController {
    private final UserCloudHistoryService userCloudHistoryService;

    public UserCloudHistoryController(UserCloudHistoryService userCloudHistoryService) {
        this.userCloudHistoryService = userCloudHistoryService;
    }

    @Operation(
            summary = "Read An EBook REST API",
            description = "Read An EBook REST API is used to read an ebook from database"
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
            summary = "Read An EBook By User REST API",
            description = "Read An EBook By User REST API is used to read an ebook by user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/books/{bookId}")
    public ResponseEntity<UserCloudHistoryDto> getReadBookByUser(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.ok(userCloudHistoryService.getUserReadBook(bookId, userId));
    }

    @Operation(
            summary = "Get Read EBook By User REST API",
            description = "Get Read EBook By User REST API is used to get all read ebooks by a user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/books")
    public ResponseEntity<UserCloudHistoryResponse> getReadBookByUser
            (@PathVariable Long userId,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(userCloudHistoryService.getAllReadBooksByUser(userId, pageNo, pageSize, sortBy, sortDir));
    }
}
