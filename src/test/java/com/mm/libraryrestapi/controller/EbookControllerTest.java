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



}