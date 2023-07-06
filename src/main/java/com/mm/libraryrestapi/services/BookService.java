package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.BookResponse;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);

    BookDto getBookById(Long id);

    BookDto updateBookById(BookDto bookDto, Long id);

    void deleteBookById(Long id);

    void updateAvailableBooks(Long bookId, int booksToAdd);

    BookResponse getBooksByTitle(String title, int pageNo, int pageSize, String sortBy, String sortDir);
    BookResponse getAllBooksByTags(String tags, int pageNo, int pageSize, String sortBy, String sortDir);
    BookResponse getAllBooksByGenre(String genre, int pageNo, int pageSize, String sortBy, String sortDir);
    BookResponse getAllBooksByPublicationYear(int publicationYear, int pageNo, int pageSize, String sortBy, String sortDir);
    BookResponse getAllBooksByAuthorName(String authorFirstName, String authorLastName, int pageNo, int pageSize, String sortBy, String sortDir);
}
