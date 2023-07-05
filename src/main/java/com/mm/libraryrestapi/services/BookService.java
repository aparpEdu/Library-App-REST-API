package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.PaperBookResponse;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    PaperBookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);

    BookDto getBookById(Long id);

    BookDto updateBookById(BookDto bookDto, Long id);

    void deleteBookById(Long id);

    void updateAvailableBooks(Long bookId, int booksToAdd);
}
