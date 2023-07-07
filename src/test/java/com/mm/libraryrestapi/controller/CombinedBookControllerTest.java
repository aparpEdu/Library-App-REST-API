package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.response.CombinedBookResponse;
import com.mm.libraryrestapi.services.CombinedBookService;
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
class CombinedBookControllerTest {

    @InjectMocks
    private CombinedBookController combinedBookController;

    @Mock
    private CombinedBookService combinedBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCombinedBooks() {
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService.getAllCombinedBooks(
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        ResponseEntity<CombinedBookResponse> response = combinedBookController
                .getAllCombinedBooks(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(combinedBookResponse, response.getBody());
    }

    @Test
    void testGetCombinedBooksByTitle() {
        String title = "Universe";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService.findByTitle(
                        title, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        ResponseEntity<CombinedBookResponse> response = combinedBookController
                .getCombinedBooksByTitle(title, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(combinedBookResponse, response.getBody());
    }

    @Test
    void testGetCombinedBooksByTags() {
        String tags = "space";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService.findByTagsContaining(
                        tags, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        ResponseEntity<CombinedBookResponse> response = combinedBookController
                .getCombinedBooksByTags(tags, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(combinedBookResponse, response.getBody());
    }

    @Test
    void testGetCombinedBooksByGenre() {
        String genre = "Science";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService.findByGenre(
                        genre, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        ResponseEntity<CombinedBookResponse> response = combinedBookController
                .getCombinedBooksByGenre(genre, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(combinedBookResponse, response.getBody());
    }

    @Test
    void testGetCombinedBooksByPublicationYear() {
        int publicationYear = 2000;
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService.findByPublicationYear(
                        publicationYear, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        ResponseEntity<CombinedBookResponse> response = combinedBookController
                .getCombinedBooksByPublicationYear(publicationYear, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(combinedBookResponse, response.getBody());
    }

    @Test
    void testGetCombinedBooksByAuthorFullName() {
        String firstName = "George";
        String lastName = "Georgiev";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService.findByAuthorFullName(
                        firstName, lastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        ResponseEntity<CombinedBookResponse> response = combinedBookController
                .getCombinedBooksByAuthorFullName(firstName, lastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(combinedBookResponse, response.getBody());
    }
}