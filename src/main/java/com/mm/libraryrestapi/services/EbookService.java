package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.EbookDto;


import com.mm.libraryrestapi.payload.EbookResponse;

public interface EbookService {
    EbookDto createEbook(EbookDto ebookDto);

    EbookResponse getAllEbooks(int pageNo, int pageSize, String sortBy, String sortDir);

    EbookDto getEbookById(Long id);

    EbookDto updateEbookById(EbookDto bookDto, Long ebookId);
    EbookDto getEbookByTitle(String title);
    EbookResponse getAllEbooksByTags(String tags, int pageNo, int pageSize, String sortBy, String sortDir);
    EbookResponse getAllEbooksByGenre(String genre, int pageNo, int pageSize, String sortBy, String sortDir);
    EbookResponse getAllEbooksByPublicationYear(int publicationYear, int pageNo, int pageSize, String sortBy, String sortDir);
    EbookResponse getAllEbooksByAuthorName(String authorFirstName, String authorLastName, int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteEbookById(Long ebookId);
}
