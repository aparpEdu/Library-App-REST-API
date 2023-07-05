package com.mm.libraryrestapi.controller;


import com.mm.libraryrestapi.payload.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.UserCloudHistoryResponse;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserCloudHistoryController {
    private final UserCloudHistoryService userCloudHistoryService;

    public UserCloudHistoryController(UserCloudHistoryService userCloudHistoryService) {
        this.userCloudHistoryService = userCloudHistoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("{userId}/read/{ebookId}")
    public ResponseEntity<UserCloudHistoryDto> readABook(@PathVariable Long userId, @PathVariable Long ebookId){
        return new ResponseEntity<>(userCloudHistoryService.readABook(ebookId, userId), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/ebooks/{ebookId}")
    public ResponseEntity<UserCloudHistoryDto> getReadBookByUser(@PathVariable Long userId, @PathVariable Long ebookId){
        return ResponseEntity.ok(userCloudHistoryService.getUserReadBook(ebookId, userId));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{userId}/ebooks")
    public ResponseEntity<UserCloudHistoryResponse> getReadBookByUser
            (@PathVariable Long userId,
             @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(userCloudHistoryService.getAllReadBooksByUser(userId, pageNo, pageSize, sortBy, sortDir));
    }
}