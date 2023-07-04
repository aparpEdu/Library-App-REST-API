package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.EbookDto;


import com.mm.libraryrestapi.payload.EbookResponse;

public interface EbookService {
    EbookDto createEbook(EbookDto ebookDto);

    EbookResponse getAllEbooks(int pageNo, int pageSize, String sortBy, String sortDir);

    EbookDto getEbookById(Long id);

    EbookDto updateEbookById(EbookDto bookDto, Long ebookId);

    void deleteEbookById(Long ebookId);
}
