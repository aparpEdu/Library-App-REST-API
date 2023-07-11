package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.dtos.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.response.BorrowHistoryResponse;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.services.BorrowHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BorrowHistoryControllerTest {
    @InjectMocks
    private BorrowHistoryController borrowHistoryController;

    @Mock
    private BorrowHistoryService borrowHistoryService;

    @Mock
    private BorrowHistoryRepository borrowHistoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        // Test data
        Long userId = 1L;
        Long bookId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();

        // Mock the service method
        when(borrowHistoryService.borrowBookById(userId, bookId)).thenReturn(borrowHistoryDto);

        // Call the controller method
        ResponseEntity<BorrowHistoryDto> response = borrowHistoryController.borrowBook(userId, bookId);

        // Verify the service method was called
        verify(borrowHistoryService).borrowBookById(userId, bookId);

        // Assert the response
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryDto, response.getBody());
    }

    @Test
    void testPostponeBorrowBook() {
        // Test data
        Long userId = 1L;
        Long borrowId = 1L;
        Long days = 7L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();

        // Mock the service method
        when(borrowHistoryService.postponeBookByHistoryId(userId, borrowId, days)).thenReturn(borrowHistoryDto);

        // Call the controller method
        ResponseEntity<BorrowHistoryDto> response = borrowHistoryController.postponeBook(userId, borrowId, days);

        // Verify the service method was called
        verify(borrowHistoryService).postponeBookByHistoryId(userId, borrowId, days);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryDto, response.getBody());
    }

    @Test
    void testReturnBook() {
        // Test data
        Long userId = 1L;
        Long borrowId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();

        // Mock the service method
        when(borrowHistoryService.returnBookByHistoryId(userId, borrowId)).thenReturn(borrowHistoryDto);

        // Call the controller method
        ResponseEntity<BorrowHistoryDto> response = borrowHistoryController.returnBook(userId, borrowId);

        // Verify the service method was called
        verify(borrowHistoryService).returnBookByHistoryId(userId, borrowId);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryDto, response.getBody());
    }

    @Test
    void testGetAllBooksBorrowedByUser() {
        // Test data
        Long userId = 1L;
        int pageNo = 1;
        int pageSize = 10;
        String sortBy = "title";
        String sortDir = "asc";
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();

        // Mock the service method
        when(borrowHistoryService.getAllBooksBorrowedByUser(userId, pageNo, pageSize, sortBy, sortDir))
                .thenReturn(borrowHistoryResponse);

        // Call the controller method
        ResponseEntity<BorrowHistoryResponse> response = borrowHistoryController.getAllBooksBorrowedByUser(
                userId, pageNo, pageSize, sortBy, sortDir);

        // Verify the service method was called
        verify(borrowHistoryService).getAllBooksBorrowedByUser(userId, pageNo, pageSize, sortBy, sortDir);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryResponse, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByBookId() {
        BorrowHistoryResponse expectedBookResponse = new BorrowHistoryResponse();
        Long userId = 1L;
        Long correctBookId = 1L;
        Mockito.when(borrowHistoryService
                .getBorrowHistoryByBookId(userId, correctBookId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(expectedBookResponse);
        ResponseEntity<BorrowHistoryResponse> response = borrowHistoryController
                .getBorrowHistoryByBookId(userId, correctBookId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedBookResponse, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByReturned() {
        // Test data
        Long userId = 1L;
        boolean returned = true;
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowHistoryService
                .getBorrowHistoryByReturned(userId, returned, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        ResponseEntity<BorrowHistoryResponse> response = borrowHistoryController
                .getBorrowHistoryByReturned(userId, returned, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryResponse, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByBorrowDate() {
        // Test data
        Long userId = 1L;
        LocalDate borrowDate = LocalDate.now();
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowHistoryService
                .getBorrowHistoryByBorrowDate(userId, borrowDate, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        ResponseEntity<BorrowHistoryResponse> response = borrowHistoryController
                .getBorrowHistoryByBorrowDate(userId, borrowDate, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryResponse, response.getBody());
    }
}
