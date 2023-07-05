package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.PaperBookDto;
import com.mm.libraryrestapi.payload.PaperBookResponse;

public interface PaperBookService {

    PaperBookDto createBook(PaperBookDto bookDto);

    PaperBookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);

    PaperBookDto getBookById(Long id);

    PaperBookDto updateBookById(PaperBookDto bookDto, Long id);

    void deleteBookById(Long id);
}
