package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.payload.dtos.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.response.UserCloudHistoryResponse;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
class UserCloudHistoryServiceImplTest {

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
        Mockito.when(userCloudHistoryService.readABook(bookId, userId)).thenReturn(userCloudHistoryDto);
        UserCloudHistoryDto createdUserCloudHistoryDto = userCloudHistoryService.readABook(bookId, userId);
        Assertions.assertEquals(userCloudHistoryDto, createdUserCloudHistoryDto);
    }

    @Test
    void testDownloadABook() {
        long userId = 1L;
        long bookId = 1L;
        UserCloudHistoryDto userCloudHistoryDto = new UserCloudHistoryDto();
        Mockito.when(userCloudHistoryService.downloadABook(bookId, userId)).thenReturn(userCloudHistoryDto);
        UserCloudHistoryDto createdUserCloudHistoryDto = userCloudHistoryService.downloadABook(bookId, userId);
        Assertions.assertEquals(userCloudHistoryDto, createdUserCloudHistoryDto);
    }

    @Test
    void testGetUserReadBook() {
        long userId = 1L;
        long bookId = 1L;
        UserCloudHistoryDto userCloudHistoryDto = new UserCloudHistoryDto();
        Mockito.when(userCloudHistoryService.getUserReadBook(bookId, userId)).thenReturn(userCloudHistoryDto);
        UserCloudHistoryDto createdUserCloudHistoryDto = userCloudHistoryService.getUserReadBook(bookId, userId);
        Assertions.assertEquals(userCloudHistoryDto, createdUserCloudHistoryDto);
    }

    @Test
    void testGetAllReadBooksByUser() {
        long userId = 1L;
        UserCloudHistoryResponse userCloudHistoryResponse = new UserCloudHistoryResponse();
        Mockito.when(userCloudHistoryService
                .getAllReadBooksByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(userCloudHistoryResponse);
        UserCloudHistoryResponse createdUserCloudHistoryResponse = userCloudHistoryService
                .getAllReadBooksByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(userCloudHistoryResponse, createdUserCloudHistoryResponse);
    }

    @Test
    void testGetAllReadBooksByReadTime() {
        LocalDateTime readTime = LocalDateTime.now();
        long userId = 1L;
        UserCloudHistoryResponse userCloudHistoryResponse = new UserCloudHistoryResponse();
        Mockito.when(userCloudHistoryService
                .getCloudHistoryByReadTime(userId, readTime, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(userCloudHistoryResponse);
        UserCloudHistoryResponse createdUserCloudHistoryResponse = userCloudHistoryService
                .getCloudHistoryByReadTime(userId, readTime, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(userCloudHistoryResponse, createdUserCloudHistoryResponse);
    }

    @Test
    void testGetAllReadBooksByDownloadTime() {
        LocalDateTime downloadTime = LocalDateTime.now();
        long userId = 1L;
        UserCloudHistoryResponse userCloudHistoryResponse = new UserCloudHistoryResponse();
        Mockito.when(userCloudHistoryService
                .getCloudHistoryByDownloadTime(userId, downloadTime, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(userCloudHistoryResponse);
        UserCloudHistoryResponse createdUserCloudHistoryResponse = userCloudHistoryService
                .getCloudHistoryByDownloadTime(userId, downloadTime, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(userCloudHistoryResponse, createdUserCloudHistoryResponse);
    }
}