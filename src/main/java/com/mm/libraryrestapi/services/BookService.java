package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.BookResponse;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);

    BookDto getBookById(long id);

    BookDto updateBookById(BookDto bookDto, long id);

    void deleteBookById(long id);
}
