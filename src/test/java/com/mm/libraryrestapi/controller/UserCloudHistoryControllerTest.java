package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.UserCloudHistoryResponse;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
class UserCloudHistoryControllerTest {

    @InjectMocks
    private UserCloudHistoryController userCloudHistoryController;

    @Mock
    private UserCloudHistoryService userCloudHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadABook() {
        long userId = 1L;
        long bookId = 1L;
        UserCloudHistoryDto userCloudHistoryDto = new UserCloudHistoryDto();
        Mockito.when(userCloudHistoryService.readABook(userId, bookId)).thenReturn(userCloudHistoryDto);
        ResponseEntity<UserCloudHistoryDto> response = userCloudHistoryController.readABook(userId, bookId);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(userCloudHistoryDto, response.getBody());
    }

    @Test
    void testGetReadBookByUser() {
        long userId = 1L;
        long bookId = 1L;
        UserCloudHistoryDto userCloudHistoryDto = new UserCloudHistoryDto();
        Mockito.when(userCloudHistoryService.getUserReadBook(bookId, userId)).thenReturn(userCloudHistoryDto);
        ResponseEntity<UserCloudHistoryDto> response = userCloudHistoryController.getReadBookByUser(userId, bookId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userCloudHistoryDto, response.getBody());
    }

    @Test
    void testGetReadBookByUserPaginating() {
        long userId = 1L;
        UserCloudHistoryResponse userCloudHistoryResponse = new UserCloudHistoryResponse();
        Mockito.when(userCloudHistoryService
                .getAllReadBooksByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(userCloudHistoryResponse);
        ResponseEntity<UserCloudHistoryResponse> response = userCloudHistoryController
                .getReadBookByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userCloudHistoryResponse, response.getBody());
    }
}