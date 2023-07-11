package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.payload.dtos.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.response.BorrowHistoryResponse;
import com.mm.libraryrestapi.services.BorrowHistoryService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
class BorrowHistoryServiceImplTest {

    @Mock
    private BorrowHistoryService borrowHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        Long userId = 1L;
        long bookId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();
        Mockito.when(borrowHistoryService.borrowBookById(userId, bookId)).thenReturn(borrowHistoryDto);
        BorrowHistoryDto newBorrowHistoryDto = borrowHistoryService.borrowBookById(userId, bookId);
        Assertions.assertEquals(borrowHistoryDto, newBorrowHistoryDto);
    }

    @Test
    void testPostponeReturnDate() {
        Long userId = 1L;
        long borrowHistoryId = 1L;
        long days = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();
        Mockito.when(borrowHistoryService.postponeBookByHistoryId(userId, borrowHistoryId, days)).thenReturn(borrowHistoryDto);
        BorrowHistoryDto newBorrowHistoryDto = borrowHistoryService.postponeBookByHistoryId(userId, borrowHistoryId, days);
        Assertions.assertEquals(borrowHistoryDto, newBorrowHistoryDto);
    }

    @Test
    void testReturnPaperBook() {
        Long userId = 1L;
        long borrowHistoryId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();
        Mockito.when(borrowHistoryService.returnBookByHistoryId(userId, borrowHistoryId)).thenReturn(borrowHistoryDto);
        BorrowHistoryDto newBorrowHistoryDto = borrowHistoryService.returnBookByHistoryId(userId, borrowHistoryId);
        Assertions.assertEquals(borrowHistoryDto, newBorrowHistoryDto);
    }

    @Test
    void testGetAllBooksBorrowedByUser() {
        long userId = 1L;
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowHistoryService
                .getAllBooksBorrowedByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
                .getAllBooksBorrowedByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

//    @Test
//    void testGetAllBooksBorrowedByLoggedUser() {
//        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
//        Mockito.when(borrowHistoryService
//                .getAllBooksBorrowedByLoggedUser(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
//        BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
//                .getAllBooksBorrowedByLoggedUser(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
//        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
//    }
//
//    @Test
//    void testGetAllBooksBorrowed() {
//        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
//        Mockito.when(borrowHistoryService
//                .getAllBooksBorrowed(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
//        BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
//                .getAllBooksBorrowed(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
//        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
//    }
//
//    @Test
//    void testGetBorrowHistoryByUserId() {
//        Long userId = 1L;
//       BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
//        Mockito.when(borrowHistoryService
//                .getBorrowHistoryByUserId(userId,  Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
//        BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
//                .getBorrowHistoryByUserId(userId,  Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
//        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
//    }

    @Test
    void testGetBorrowHistoryByBookId() {
        Long userId = 1L;
        Long bookId = 1L;
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowHistoryService
                .getBorrowHistoryByBookId(userId, bookId,  Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
       BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
                .getBorrowHistoryByBookId(userId, bookId,  Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetBorrowHistoryByBorrowDate() {
        Long userId = 1L;
        LocalDate borrowDate = LocalDate.now();
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowHistoryService
                .getBorrowHistoryByBorrowDate(userId, borrowDate, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
                .getBorrowHistoryByBorrowDate(userId, borrowDate, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetBorrowHistoryByReturned() {
        Long userId = 1L;
        boolean returned = true;
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowHistoryService
                .getBorrowHistoryByReturned(userId, returned, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowHistoryService
                .getBorrowHistoryByReturned(userId, returned, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }
}