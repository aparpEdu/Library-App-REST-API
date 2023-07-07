package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BookResponse;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.services.BorrowBookService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BorrowBookControllerTest {
    @InjectMocks
    private BorrowBookController borrowBookController;

    @Mock
    private BorrowBookService borrowBookService;

    @Mock
    private BorrowHistoryRepository borrowHistoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        // Test data
        Long bookId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();

        // Mock the service method
        when(borrowBookService.borrowBook(bookId)).thenReturn(borrowHistoryDto);

        // Call the controller method
        ResponseEntity<BorrowHistoryDto> response = borrowBookController.borrowBook(bookId);

        // Verify the service method was called
        verify(borrowBookService).borrowBook(bookId);

        // Assert the response
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryDto, response.getBody());
    }

    @Test
    void testPostponeBorrowBook() {
        // Test data
        Long borrowId = 1L;
        Long days = 7L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();

        // Mock the service method
        when(borrowBookService.postponeReturnDate(borrowId, days)).thenReturn(borrowHistoryDto);

        // Call the controller method
        ResponseEntity<BorrowHistoryDto> response = borrowBookController.borrowBook(borrowId, days);

        // Verify the service method was called
        verify(borrowBookService).postponeReturnDate(borrowId, days);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryDto, response.getBody());
    }

    @Test
    void testReturnBook() {
        // Test data
        Long borrowId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();

        // Mock the service method
        when(borrowBookService.returnPaperBook(borrowId)).thenReturn(borrowHistoryDto);

        // Call the controller method
        ResponseEntity<BorrowHistoryDto> response = borrowBookController.returnBook(borrowId);

        // Verify the service method was called
        verify(borrowBookService).returnPaperBook(borrowId);

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
        when(borrowBookService.getAllBooksBorrowedByUser(userId, pageNo, pageSize, sortBy, sortDir))
                .thenReturn(borrowHistoryResponse);

        // Call the controller method
        ResponseEntity<BorrowHistoryResponse> response = borrowBookController.getAllBooksBorrowedByUser(
                userId, pageNo, pageSize, sortBy, sortDir);

        // Verify the service method was called
        verify(borrowBookService).getAllBooksBorrowedByUser(userId, pageNo, pageSize, sortBy, sortDir);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryResponse, response.getBody());
    }

    @Test
    void testGetAllBooksBorrowedByLoggedUser() {
        // Test data
        int pageNo = 1;
        int pageSize = 10;
        String sortBy = "title";
        String sortDir = "asc";
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();

        // Mock the service method
        when(borrowBookService.getAllBooksBorrowedByLoggedUser(pageNo, pageSize, sortBy, sortDir))
                .thenReturn(borrowHistoryResponse);

        // Call the controller method
        ResponseEntity<BorrowHistoryResponse> response = borrowBookController.getAllBooksBorrowedByLoggedUser(
                pageNo, pageSize, sortBy, sortDir);

        // Verify the service method was called
        verify(borrowBookService).getAllBooksBorrowedByLoggedUser(pageNo, pageSize, sortBy, sortDir);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryResponse, response.getBody());
    }

    @Test
    void testGetAllBooksBorrowed() {
        // Test data
        int pageNo = 1;
        int pageSize = 10;
        String sortBy = "title";
        String sortDir = "asc";
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();

        // Mock the service method
        when(borrowBookService.getAllBooksBorrowed(pageNo, pageSize, sortBy, sortDir))
                .thenReturn(borrowHistoryResponse);

        // Call the controller method
        ResponseEntity<BorrowHistoryResponse> response = borrowBookController.getAllBooksBorrowed(
                pageNo, pageSize, sortBy, sortDir);

        // Verify the service method was called
        verify(borrowBookService).getAllBooksBorrowed(pageNo, pageSize, sortBy, sortDir);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryResponse, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByUserId() {
        BorrowHistoryResponse expectedBookResponse = new BorrowHistoryResponse();
        Long correctUserId = 1L;
        Mockito.when(borrowBookService
                .getBorrowHistoryByUserId(correctUserId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(expectedBookResponse);
        ResponseEntity<BorrowHistoryResponse> response = borrowBookController
                .getBorrowHistoryByUserId(correctUserId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedBookResponse, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByBookId() {
        BorrowHistoryResponse expectedBookResponse = new BorrowHistoryResponse();
        Long correctBookId = 1L;
        Mockito.when(borrowBookService
                .getBorrowHistoryByBookId(correctBookId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(expectedBookResponse);
        ResponseEntity<BorrowHistoryResponse> response = borrowBookController
                .getBorrowHistoryByBookId(correctBookId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedBookResponse, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByReturned() {
        // Test data
        boolean returned = true;
        List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.getBorrowHistoryByReturned(returned);

        // Call the controller method
        ResponseEntity<List<BorrowHistory>> response = borrowBookController.getBorrowHistoryByReturned(returned);

        // Verify the repository method was called
        verify(borrowHistoryRepository).getBorrowHistoryByReturned(returned);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryList, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByBorrowDate() {
        // Test data
        LocalDate borrowDate = LocalDate.now();
        List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.getBorrowHistoryByBorrowDate(borrowDate);

        // Call the controller method
        ResponseEntity<List<BorrowHistory>> response = borrowBookController.getBorrowHistoryByBorrowDate(borrowDate);

        // Verify the repository method was called
        verify(borrowHistoryRepository).getBorrowHistoryByBorrowDate(borrowDate);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryList, response.getBody());
    }
}
