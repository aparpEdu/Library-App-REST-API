package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.services.BorrowBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
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
        // Test data
        Long userId = 1L;
        List<BorrowHistory> borrowHistoryList = List.of(new BorrowHistory());

        // Mock the repository method
        when(borrowHistoryRepository.getBorrowHistoryByUserId(userId)).thenReturn(borrowHistoryList);

        // Call the controller method
        ResponseEntity<List<BorrowHistory>> response = borrowBookController.getBorrowHistoryByUserId(userId);

        // Verify the repository method was called
        verify(borrowHistoryRepository).getBorrowHistoryByUserId(userId);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryList, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByBookId() {
        // Test data
        Long bookId = 1L;
        List<BorrowHistory> borrowHistoryList = List.of(new BorrowHistory());

        // Mock the repository method
        when(borrowHistoryRepository.getBorrowHistoryByBookId(bookId)).thenReturn(borrowHistoryList);

        // Call the controller method
        ResponseEntity<List<BorrowHistory>> response = borrowBookController.getBorrowHistoryByBookId(bookId);

        // Verify the repository method was called
        verify(borrowHistoryRepository).getBorrowHistoryByBookId(bookId);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryList, response.getBody());
    }

    @Test
    void testGetBorrowHistoryByReturned() {
        // Test data
        boolean returned = true;
        List<BorrowHistory> borrowHistoryList = List.of(new BorrowHistory());

        // Mock the repository method
        when(borrowHistoryRepository.getBorrowHistoryByReturned(returned)).thenReturn(borrowHistoryList);

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
        List<BorrowHistory> borrowHistoryList = List.of(new BorrowHistory());

        // Mock the repository method
        when(borrowHistoryRepository.getBorrowHistoryByBorrowDate(borrowDate)).thenReturn(borrowHistoryList);

        // Call the controller method
        ResponseEntity<List<BorrowHistory>> response = borrowBookController.getBorrowHistoryByBorrowDate(borrowDate);

        // Verify the repository method was called
        verify(borrowHistoryRepository).getBorrowHistoryByBorrowDate(borrowDate);

        // Assert the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(borrowHistoryList, response.getBody());
    }
}
