package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;
import com.mm.libraryrestapi.services.AuthorService;
import com.mm.libraryrestapi.utils.AppConstants;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthor() {
        AuthorDto authorDto = new AuthorDto();
        Mockito.when(authorService.createAuthor(authorDto)).thenReturn(authorDto);
        AuthorDto createdAuthor = authorService.createAuthor(authorDto);
        Assert.assertEquals(authorDto, createdAuthor);
    }

    @Test
    void testGetAllAuthors() {
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                .getAllAuthors(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(authorResponse);
        AuthorResponse createdAuthorResponse = authorService
                .getAllAuthors(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assert.assertEquals(authorResponse, createdAuthorResponse);
    }

    @Test
    void testGetAuthorById() {
        long authorId = 1L;
        AuthorDto authorDto = new AuthorDto();
        Mockito.when(authorService.getAuthorById(authorId)).thenReturn(authorDto);
        AuthorDto createdAuthor = authorService.getAuthorById(authorId);
        Assert.assertEquals(authorDto, createdAuthor);
    }

    @Test
    void testUpdateAuthorById() {
        long authorId = 1L;
        AuthorDto authorDto = new AuthorDto();
        Mockito.when(authorService.updateAuthorById(authorDto, authorId)).thenReturn(authorDto);
        AuthorDto updatedAuthor = authorService.updateAuthorById(authorDto, authorId);
        Assert.assertEquals(authorDto, updatedAuthor);
    }

    @Test
    void testDeleteAuthorById() {
        long authorId = 1L;
        authorService.deleteAuthorById(authorId);
        Mockito.verify(authorService, Mockito.times(1)).deleteAuthorById(authorId);
    }

    @Test
    void testGetAuthorByFirstName() {
        String firstName = "Daniel";
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                .getAuthorByFirstName(firstName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(authorResponse);
        AuthorResponse createdAuthorResponse = authorService
                .getAuthorByFirstName(firstName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assert.assertEquals(authorResponse, createdAuthorResponse);
    }

    @Test
    void testGetAuthorByLastName() {
        String lastName = "Baykov";
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                .getAuthorByLastName(lastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(authorResponse);
        AuthorResponse createdAuthorResponse = authorService
                .getAuthorByLastName(lastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assert.assertEquals(authorResponse, createdAuthorResponse);
    }

    @Test
    void testGetAuthorByCountry() {
        String country = "Somalia";
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                .getAuthorByCountry(country, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(authorResponse);
        AuthorResponse createdAuthorResponse = authorService
                .getAuthorByCountry(country, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assert.assertEquals(authorResponse, createdAuthorResponse);
    }
}