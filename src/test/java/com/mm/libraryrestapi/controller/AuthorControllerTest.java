package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;
import com.mm.libraryrestapi.services.AuthorService;
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
class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

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
        ResponseEntity<AuthorDto> response = authorController.createAuthor(authorDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(authorDto, response.getBody());
    }

    @Test
    void testGetAuthor() {
        long id = 1L;
        AuthorDto authorDto = new AuthorDto();
        Mockito.when(authorService.getAuthorById(id)).thenReturn(authorDto);
        ResponseEntity<AuthorDto> response = authorController.getAuthor(id);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(authorDto, response.getBody());
    }

    @Test
    void testDeleteAuthorById() {
        long id = 1L;
        ResponseEntity<String> response = authorController.deleteAuthorById(id);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Author was successfully deleted", response.getBody());
    }

    @Test
    void testGetAllAuthors() {
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                .getAllAuthors(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)).thenReturn(authorResponse);
        ResponseEntity<AuthorResponse> response = authorController
                .getAllAuthors(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(authorResponse, response.getBody());
    }

    @Test
    void testUpdateAuthorById() {
        long id = 1L;
        AuthorDto authorDto = new AuthorDto();
        Mockito.when(authorService.updateAuthorById(authorDto, id)).thenReturn(authorDto);
        ResponseEntity<AuthorDto> response = authorController.updateAuthorById(id, authorDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(authorDto, response.getBody());
    }

    @Test
    void testGetAuthorsByFirstName() {
        String firstName = "Daniel";
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                        .getAuthorByFirstName(firstName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(authorResponse);
        ResponseEntity<AuthorResponse> response = authorController
                .getAuthorsByFirstName(firstName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(authorResponse, response.getBody());
    }

    @Test
    void testGetAuthorsByLastName() {
        String lastName = "Baykov";
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                        .getAuthorByLastName(lastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(authorResponse);
        ResponseEntity<AuthorResponse> response = authorController
                .getAuthorsByLastName(lastName, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(authorResponse, response.getBody());
    }

    @Test
    void testGetAuthorsByCountry() {
        String country = "Somalia";
        AuthorResponse authorResponse = new AuthorResponse();
        Mockito.when(authorService
                        .getAuthorByCountry(country, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
                .thenReturn(authorResponse);
        ResponseEntity<AuthorResponse> response = authorController
                .getAuthorsByCountry(country, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(authorResponse, response.getBody());
    }
}