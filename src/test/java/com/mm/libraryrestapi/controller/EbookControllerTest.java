package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.EbookDto;
import com.mm.libraryrestapi.payload.EbookResponse;
import com.mm.libraryrestapi.services.EbookService;
import org.junit.Assert;
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
class EbookControllerTest {
    @InjectMocks
    private EbookController ebookController;

    @Mock
    private EbookService ebookService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOKResponseAndProperEbookWhenProvidingCorrectId() {
        Long ebookId = 1L;
        EbookDto expectedEbook = new EbookDto();
        //Configuring the behavior so that when calling getEbookById, should return the expectedEbook
        Mockito.when(ebookService.getEbookById(ebookId)).thenReturn(expectedEbook);
        ResponseEntity<EbookDto> receivedResponse = ebookController.getEbookById(ebookId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbook, receivedResponse.getBody());
    }
    @Test
    void shouldNotReturnProperEbookWhenProvidingIncorrectId() {
        Long correctEbookId = 1L;
        Long incorrectEbookId = 2L;
        EbookDto expectedEbook = new EbookDto();
        //Configuring the behavior so that when calling getEbookById, should return the expectedEbook
        Mockito.when(ebookService.getEbookById(correctEbookId)).thenReturn(expectedEbook);
        ResponseEntity<EbookDto> receivedResponse = ebookController.getEbookById(incorrectEbookId);
        Assert.assertNotEquals(expectedEbook, receivedResponse.getBody());
    }
    @Test
    void shouldCreateAndInsertEbookAndReturnCreatedResponse(){
        EbookDto ebookToInsert = new EbookDto();
        Mockito.when(ebookService.createEbook(ebookToInsert)).thenReturn(ebookToInsert);
        ResponseEntity<EbookDto> receivedResponse = ebookController.createEbook(ebookToInsert);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(ebookToInsert, receivedResponse.getBody());
    }
    @Test
    void shouldDeleteEbookFromDatabaseAndReturnStatusOK(){
        EbookDto book = new EbookDto(1L, "Universe", "space ships universe", "New smart summary",
                "9876543567892", "Science", 1L, 2000,
                "https://mail.google.com/mail/u/4/#chat/dm/9yUUMUAAAAE",
                "https://mail.google.com/mail/u/4/#chat/dm/9yUUMUAAAAE");
        ebookService.createEbook(book);
        ResponseEntity<String> response = ebookController.deleteEbookById(book.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Ebook was successfully deleted", response.getBody());
    }

    @Test
    void shouldUpdateEbookFromDatabaseAndReturnStatusOKWhenProvidedCorrectId(){
        EbookDto book = new EbookDto(1L, "Universe", "space ships universe", "New smart summary",
                "9876543567892", "Science", 1L, 2000,
                "https://mail.google.com/mail/u/4/#chat/dm/9yUUMUAAAAE",
                "https://mail.google.com/mail/u/4/#chat/dm/9yUUMUAAAAE");
        Long correctEbookId = 1L;;
        Mockito.when(ebookService.updateEbookById(book, correctEbookId)).thenReturn(book);
        ResponseEntity<EbookDto> receivedResponse = ebookController.updateEbookById(book, correctEbookId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals(book, receivedResponse.getBody());
    }
    @Test
    void shouldNotUpdateEbookFromDatabaseAndReturnStatusOKWhenProvidedIncorrectId(){
        EbookDto book = new EbookDto(1L, "Universe", "space ships universe", "New smart summary",
                "9876543567892", "Science", 1L, 2000,
                "https://mail.google.com/mail/u/4/#chat/dm/9yUUMUAAAAE",
                "https://mail.google.com/mail/u/4/#chat/dm/9yUUMUAAAAE");
        Long correctEbookId = 1L;;
        Long incorrectEbookId = 2L;
        Mockito.when(ebookService.updateEbookById(book, correctEbookId)).thenReturn(book);
        ResponseEntity<EbookDto> receivedResponse = ebookController.updateEbookById(book, incorrectEbookId);
        Assertions.assertNotEquals(book, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllEbooksPaginated(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooks(pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getAllEbooks(pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllEbooksByTags(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String tags = "Henry IV";
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooksByTags(tags,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getEbooksByTags(tags, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnEbookByTitle(){
        String title = "Henry IV";
        EbookDto expectedEbook = new EbookDto();
        Mockito.when(ebookService.getEbookByTitle(title)).thenReturn(expectedEbook);
        ResponseEntity<EbookDto> receivedResponse = ebookController.getEBookByTitle(title);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbook, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllEbooksByGenre(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String genre = "Manga";
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooksByGenre(genre,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getEbooksByGenre(genre, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }
    @Test
    void shouldReturnAllEbooksByPublicationYear(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        int publicationYear = 2003;
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooksByPublicationYear(publicationYear,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getEBookByPublicationYear(publicationYear, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllEbooksByAuthorFirstName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "Alexander";
        String authorLastName = "";
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getEBookByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllEbooksByAuthorLastName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "";
        String authorLastName = "The Great";
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getEBookByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllEbooksByAuthorFirstNameAndLastName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "Alexander";
        String authorLastName = "The Great";
        EbookResponse expectedEbookResponse = new EbookResponse();
        Mockito.when(ebookService.getAllEbooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbookResponse);
        ResponseEntity<EbookResponse> receivedResponse = ebookController.getEBookByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedEbookResponse, receivedResponse.getBody());
    }

}