package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.BookResponse;
import com.mm.libraryrestapi.services.BookService;
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
class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOKResponseAndProperBookWhenProvidingCorrectId() {
        Long bookId = 1L;
        BookDto expectedBook = new BookDto();
        //Configuring the behavior so that when calling getBookById, should return the expectedBook
        Mockito.when(bookService.getBookById(bookId)).thenReturn(expectedBook);
        ResponseEntity<BookDto> receivedResponse = bookController.getBookById(bookId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBook, receivedResponse.getBody());
    }
    @Test
    void shouldNotReturnProperBookWhenProvidingIncorrectId() {
        Long correctBookId = 1L;
        Long incorrectBookId = 2L;
        BookDto expectedBook = new BookDto();
        //Configuring the behavior so that when calling getBookById, should return the expectedBook
        Mockito.when(bookService.getBookById(correctBookId)).thenReturn(expectedBook);
        ResponseEntity<BookDto> receivedResponse = bookController.getBookById(incorrectBookId);
        Assert.assertNotEquals(expectedBook, receivedResponse.getBody());
    }
    @Test
    void shouldCreateBookAndReturnCreatedResponse(){
        BookDto BookToInsert = new BookDto();
        Mockito.when(bookService.createBook(BookToInsert)).thenReturn(BookToInsert);
        ResponseEntity<BookDto> receivedResponse = bookController.createBook(BookToInsert);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(BookToInsert, receivedResponse.getBody());
    }
    @Test
    void shouldDeleteBookFromDatabaseAndReturnStatusOK(){
        BookDto book = new BookDto(1L, "Universe", "space ships universe", "New smart summary",
                "9876543567892", "Science", 1L, 2000,
                10,
                10);
        bookService.createBook(book);
        ResponseEntity<String> response = bookController.deleteBookById(book.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Book was successfully deleted", response.getBody());
    }

    @Test
    void shouldUpdateBookFromDatabaseAndReturnStatusOKWhenProvidedCorrectId(){
        BookDto book = new BookDto(1L, "Universe", "space ships universe", "New smart summary",
                "9876543567892", "Science", 1L, 2000,
                10,
                10);
        Long correctBookId = 1L;
        Mockito.when(bookService.updateBookById(book, correctBookId)).thenReturn(book);
        ResponseEntity<BookDto> receivedResponse = bookController.updateBookById(book, correctBookId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals(book, receivedResponse.getBody());
    }
    @Test
    void shouldNotUpdateBookFromDatabaseAndReturnStatusOKWhenProvidedIncorrectId(){
        BookDto book = new BookDto(1L, "Universe", "space ships universe", "New smart summary",
                "9876543567892", "Science", 1L, 2000,
                10,
                10);
        Long correctBookId = 1L;
        Long incorrectBookId = 2L;
        Mockito.when(bookService.updateBookById(book, correctBookId)).thenReturn(book);
        ResponseEntity<BookDto> receivedResponse = bookController.updateBookById(book, incorrectBookId);
        Assertions.assertNotEquals(book, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllBooksPaginated(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooks(pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getAllBooks(pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllBooksByTags(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String tags = "Henry IV";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooksByTags(tags,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getBooksByTags(tags, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnBookByTitle(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String title = "Henry IV";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getBooksByTitle(title,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getBooksByTitle(title,pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllBooksByGenre(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String genre = "Manga";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooksByGenre(genre,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getBooksByGenre(genre, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }
    @Test
    void shouldReturnAllBooksByPublicationYear(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        int publicationYear = 2003;
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooksByPublicationYear(publicationYear,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getEBookByPublicationYear(publicationYear, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllBooksByAuthorFirstName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "Alexander";
        String authorLastName = "";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getBookByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllBooksByAuthorLastName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "";
        String authorLastName = "The Great";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getBookByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllBooksByAuthorFirstNameAndLastName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "Alexander";
        String authorLastName = "The Great";
        BookResponse expectedBookResponse = new BookResponse();
        Mockito.when(bookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBookResponse);
        ResponseEntity<BookResponse> receivedResponse = bookController.getBookByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedBookResponse, receivedResponse.getBody());
    }

}