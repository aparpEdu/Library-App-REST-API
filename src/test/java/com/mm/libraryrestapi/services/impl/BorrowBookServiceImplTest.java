package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;
import com.mm.libraryrestapi.services.BorrowBookService;
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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class BorrowBookServiceImplTest {

    @Mock
    private BorrowBookService borrowBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        long bookId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();
        Mockito.when(borrowBookService.borrowBook(bookId)).thenReturn(borrowHistoryDto);
        BorrowHistoryDto newBorrowHistoryDto = borrowBookService.borrowBook(bookId);
        Assertions.assertEquals(borrowHistoryDto, newBorrowHistoryDto);
    }

    @Test
    void testPostponeReturnDate() {
        long borrowHistoryId = 1L;
        long days = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();
        Mockito.when(borrowBookService.postponeReturnDate(borrowHistoryId, days)).thenReturn(borrowHistoryDto);
        BorrowHistoryDto newBorrowHistoryDto = borrowBookService.postponeReturnDate(borrowHistoryId, days);
        Assertions.assertEquals(borrowHistoryDto, newBorrowHistoryDto);
    }

    @Test
    void testReturnPaperBook() {
        long borrowHistoryId = 1L;
        BorrowHistoryDto borrowHistoryDto = new BorrowHistoryDto();
        Mockito.when(borrowBookService.returnPaperBook(borrowHistoryId)).thenReturn(borrowHistoryDto);
        BorrowHistoryDto newBorrowHistoryDto = borrowBookService.returnPaperBook(borrowHistoryId);
        Assertions.assertEquals(borrowHistoryDto, newBorrowHistoryDto);
    }

    @Test
    void testGetAllBooksBorrowedByUser() {
        long userId = 1L;
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowBookService
                .getAllBooksBorrowedByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowBookService
                .getAllBooksBorrowedByUser(userId, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetAllBooksBorrowedByLoggedUser() {
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowBookService
                .getAllBooksBorrowedByLoggedUser(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowBookService
                .getAllBooksBorrowedByLoggedUser(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetAllBooksBorrowed() {
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowBookService
                .getAllBooksBorrowed(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowBookService
                .getAllBooksBorrowed(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetBorrowHistoryByUserId() {
        long userId = 1L;
        List<BorrowHistory> borrowHistoryResponse = new ArrayList<>();
        Mockito.when(borrowBookService
                .getBorrowHistoryByUserId(userId)).thenReturn(borrowHistoryResponse);
        List<BorrowHistory> newBorrowHistoryResponse = borrowBookService
                .getBorrowHistoryByUserId(userId);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetBorrowHistoryByBookId() {
        long bookId = 1L;
        List<BorrowHistory> borrowHistoryResponse = new ArrayList<>();
        Mockito.when(borrowBookService
                .getBorrowHistoryByBookId(bookId)).thenReturn(borrowHistoryResponse);
        List<BorrowHistory> newBorrowHistoryResponse = borrowBookService
                .getBorrowHistoryByBookId(bookId);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetBorrowHistoryByBorrowDate() {
        LocalDate borrowDate = LocalDate.now();
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowBookService
                .getBorrowHistoryByBorrowDate(borrowDate, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowBookService
                .getBorrowHistoryByBorrowDate(borrowDate, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }

    @Test
    void testGetBorrowHistoryByReturned() {
        boolean returned = true;
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        Mockito.when(borrowBookService
                .getBorrowHistoryByReturned(returned, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(borrowHistoryResponse);
        BorrowHistoryResponse newBorrowHistoryResponse = borrowBookService
                .getBorrowHistoryByReturned(returned, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(borrowHistoryResponse, newBorrowHistoryResponse);
    }
}