package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorResponse getAllAuthors(int pageNo, int pageSize, String sortBy, String sortDir);

    AuthorDto getAuthorById(long id);

    AuthorDto updateAuthorById(AuthorDto authorDto, long id);

    void deleteAuthorById(long id);

    AuthorResponse getAuthorByFirstName(String firstName, int pageNo, int pageSize, String sortBy, String sortDir);

    AuthorResponse getAuthorByLastName(String lastName, int pageNo, int pageSize, String sortBy, String sortDir);

    AuthorResponse getAuthorByCountry(String country, int pageNo, int pageSize, String sortBy, String sortDir);
}
