package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.payload.EbookDto;
import com.mm.libraryrestapi.payload.EbookResponse;
import com.mm.libraryrestapi.services.EbookService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class EbookServiceImplTest {
    @Mock
    private EbookService ebookService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldCreateEbookAndReturnEbookDto() {
        EbookDto ebookToAdd = new EbookDto();
        Mockito.when(ebookService.createEbook(ebookToAdd)).thenReturn(ebookToAdd);
        EbookDto createdEbook = ebookService.createEbook(ebookToAdd);
        Assert.assertEquals(ebookToAdd, createdEbook);
    }

    @Test
    void shouldReturnAllEbooksPaginated() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        EbookResponse expectedEbooks = new EbookResponse();
        Mockito.when(ebookService.getAllEbooks(pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedEbooks);
        EbookResponse returnedEbooks  = ebookService.getAllEbooks(pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedEbooks, returnedEbooks);
    }

    @Test
    void shouldReturnEbookWhenProvidedWithCorrectId() {
        Long correctEbookId = 1L;
        EbookDto expectedEbook = new EbookDto();
        Mockito.when(ebookService.getEbookById(correctEbookId)).thenReturn(expectedEbook);
        EbookDto returnedEbook = ebookService.getEbookById(correctEbookId);
        Assert.assertEquals(expectedEbook, returnedEbook);
    }
    @Test
    void shouldNotReturnEbookWhenProvidedWithIncorrectId() {
        Long correctEbookId = 1L;
        Long incorrectEbookId = 2L;
        EbookDto expectedEbook = new EbookDto();
        Mockito.when(ebookService.getEbookById(correctEbookId)).thenReturn(expectedEbook);
        EbookDto returnedEbook = ebookService.getEbookById(incorrectEbookId);
        Assert.assertNotEquals(expectedEbook, returnedEbook);
    }

    @Test
    void shouldUpdateEbookWhenProvidedCorrectId() {
        EbookDto newEbookData = new EbookDto();
        Long correctEbookId = 1L;
        Mockito.when(ebookService.updateEbookById(newEbookData, correctEbookId)).thenReturn(newEbookData);
        EbookDto updatedBook = ebookService.updateEbookById(newEbookData, correctEbookId);
        Assert.assertEquals(newEbookData, updatedBook);
    }

    @Test
    void shouldNotUpdateEbookWhenProvidedIncorrectId() {
        EbookDto newEbookData = new EbookDto();
        Long correctEbookId = 1L;
        Long incorrectEbookId = 2L;
        Mockito.when(ebookService.updateEbookById(newEbookData, correctEbookId)).thenReturn(newEbookData);
        EbookDto updatedBook = ebookService.updateEbookById(newEbookData, incorrectEbookId);
        Assert.assertNotEquals(newEbookData, updatedBook);
    }

    @Test
    void deleteEbookById() {
    }

    @Test
    void getEbookByTitle() {
    }

    @Test
    void getAllEbooksByTags() {
    }

    @Test
    void getAllEbooksByGenre() {
    }

    @Test
    void getAllEbooksByPublicationYear() {
    }

    @Test
    void getAllEbooksByAuthorName() {
    }
}