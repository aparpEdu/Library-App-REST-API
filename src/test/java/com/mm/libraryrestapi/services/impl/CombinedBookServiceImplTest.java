package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.payload.CombinedBookResponse;
import com.mm.libraryrestapi.services.CombinedBookService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CombinedBookServiceImplTest {

    @Mock
    private CombinedBookService combinedBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCombinedBooks() {
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService
                        .getAllCombinedBooks(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        CombinedBookResponse newCombinedBookResponse = combinedBookService
                .getAllCombinedBooks(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(combinedBookResponse, newCombinedBookResponse);
    }

    @Test
    void testFindByAuthorFullName() {
        String authorFirstName = "Daniel";
        String authorLastName = "Baykov";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService
                        .findByAuthorFullName(authorFirstName, authorLastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        CombinedBookResponse newCombinedBookResponse = combinedBookService
                .findByAuthorFullName(authorFirstName, authorLastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(combinedBookResponse, newCombinedBookResponse);
    }

    @Test
    void testFindByPublicationYear() {
        int publicationYear = 2000;
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService
                        .findByPublicationYear(publicationYear, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        CombinedBookResponse newCombinedBookResponse = combinedBookService
                .findByPublicationYear(publicationYear, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(combinedBookResponse, newCombinedBookResponse);
    }

    @Test
    void testFindByGenre() {
        String genre = "Science";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService
                        .findByGenre(genre, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        CombinedBookResponse newCombinedBookResponse = combinedBookService
                .findByGenre(genre, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(combinedBookResponse, newCombinedBookResponse);
    }

    @Test
    void testFindByTagsContaining() {
        String tags = "space";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService
                        .findByTagsContaining(tags, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        CombinedBookResponse newCombinedBookResponse = combinedBookService
                .findByTagsContaining(tags, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(combinedBookResponse, newCombinedBookResponse);
    }

    @Test
    void testFindByTitle() {
        String title = "Universe";
        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
        Mockito.when(combinedBookService
                        .findByTitle(title, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(combinedBookResponse);
        CombinedBookResponse newCombinedBookResponse = combinedBookService
                .findByTitle(title, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(combinedBookResponse, newCombinedBookResponse);
    }
}