package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.CombinedBookResponse;


public interface CombinedBookService {
    CombinedBookResponse getAllCombinedBooks(int pageNo, int pageSize, String sortBy, String sortDir);
    CombinedBookResponse findByAuthorId(int authorId, int pageNo, int pageSize, String sortBy, String sortDir);

    CombinedBookResponse findByPublicationYear(int publicationYear, int pageNo, int pageSize, String sortBy, String sortDir);

    CombinedBookResponse findByGenre(String genre, int pageNo, int pageSize, String sortBy, String sortDir);

    CombinedBookResponse findBySummaryContaining(String summary, int pageNo, int pageSize, String sortBy, String sortDir);

    CombinedBookResponse findByTagsContaining(String tags, int pageNo, int pageSize, String sortBy, String sortDir);

    CombinedBookResponse findByTitle(String title, int pageNo, int pageSize, String sortBy, String sortDir);

}
