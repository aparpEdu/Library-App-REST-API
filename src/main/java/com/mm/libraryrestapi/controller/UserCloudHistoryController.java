package com.mm.libraryrestapi.controller;


import com.mm.libraryrestapi.payload.UserCloudHistoryDto;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserCloudHistoryController {
    private final UserCloudHistoryService userCloudHistoryService;

    public UserCloudHistoryController(UserCloudHistoryService userCloudHistoryService) {
        this.userCloudHistoryService = userCloudHistoryService;
    }

    @PostMapping("{userId}/read/{ebookId}")
    public ResponseEntity<UserCloudHistoryDto> readABook(@PathVariable Long userId, @PathVariable Long ebookId){
        return new ResponseEntity<>(userCloudHistoryService.readABook(ebookId, userId), HttpStatus.CREATED);
    }
}
