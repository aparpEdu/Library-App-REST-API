package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.BookResponse;
import com.mm.libraryrestapi.services.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class BookServiceImplTest {
    @Mock
    private BookService BookService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldCreateBookAndReturnBookDto() {
        BookDto BookToAdd = new BookDto();
        Mockito.when(BookService.createBook(BookToAdd)).thenReturn(BookToAdd);
        BookDto createdBook = BookService.createBook(BookToAdd);
        Assert.assertEquals(BookToAdd, createdBook);
    }

    @Test
    void shouldReturnAllBooksPaginated() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooks(pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks  = BookService.getAllBooks(pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    void shouldReturnBookWhenProvidedWithCorrectId() {
        Long correctBookId = 1L;
        BookDto expectedBook = new BookDto();
        Mockito.when(BookService.getBookById(correctBookId)).thenReturn(expectedBook);
        BookDto returnedBook = BookService.getBookById(correctBookId);
        Assert.assertEquals(expectedBook, returnedBook);
    }
    @Test
    void shouldNotReturnBookWhenProvidedWithIncorrectId() {
        Long correctBookId = 1L;
        Long incorrectBookId = 2L;
        BookDto expectedBook = new BookDto();
        Mockito.when(BookService.getBookById(correctBookId)).thenReturn(expectedBook);
        BookDto returnedBook = BookService.getBookById(incorrectBookId);
        Assert.assertNotEquals(expectedBook, returnedBook);
    }

    @Test
    void shouldUpdateBookWhenProvidedCorrectId() {
        BookDto newBookData = new BookDto();
        Long correctBookId = 1L;
        Mockito.when(BookService.updateBookById(newBookData, correctBookId)).thenReturn(newBookData);
        BookDto updatedBook = BookService.updateBookById(newBookData, correctBookId);
        Assert.assertEquals(newBookData, updatedBook);
    }

    @Test
    void shouldNotUpdateBookWhenProvidedIncorrectId() {
        BookDto newBookData = new BookDto();
        Long correctBookId = 1L;
        Long incorrectBookId = 2L;
        Mockito.when(BookService.updateBookById(newBookData, correctBookId)).thenReturn(newBookData);
        BookDto updatedBook = BookService.updateBookById(newBookData, incorrectBookId);
        Assert.assertNotEquals(newBookData, updatedBook);
    }


    @Test
    void shouldReturnBookByTitle() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String title = "Henry IV";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getBooksByTitle(title,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBook = BookService.getBooksByTitle(title,pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBook);
    }

    @Test
    void shouldReturnAllBooksByTags() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String tags = "Henry IV";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooksByTags(tags,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks = BookService.getAllBooksByTags(tags, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    void shouldReturnAllBooksByGenre() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String genre = "Comedy";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooksByGenre(genre,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks = BookService.getAllBooksByGenre(genre, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    void shouldReturnAllBooksByPublicationYear() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        int publicationYear = 2003;
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooksByPublicationYear(publicationYear,pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks = BookService.getAllBooksByPublicationYear(publicationYear, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    void shouldReturnAllBooksByAuthorFirstName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "Alexander";
        String authorLastName = "";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks = BookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    void shouldReturnAllBooksByAuthorLastName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "";
        String authorLastName = "The great";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks = BookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    void shouldReturnAllBooksByAuthorFirstNameAndLastName(){
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "id";
        String sortDirection = "asc";
        String authorFirstName = "Alexander";
        String authorLastName = "The great";
        BookResponse expectedBooks = new BookResponse();
        Mockito.when(BookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection)).thenReturn(expectedBooks);
        BookResponse returnedBooks = BookService.getAllBooksByAuthorName(authorFirstName,authorLastName, pageNumber, pageSize, sortBy, sortDirection);
        Assert.assertEquals(expectedBooks, returnedBooks);
    }
}