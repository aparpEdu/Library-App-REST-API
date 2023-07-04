package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Author;
import com.mm.libraryrestapi.entity.Ebook;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.EbookDto;
import com.mm.libraryrestapi.payload.EbookResponse;
import com.mm.libraryrestapi.repositories.AuthorRepository;
import com.mm.libraryrestapi.repositories.EbookRepository;
import com.mm.libraryrestapi.services.EbookService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    private final CustomMapper mapper;
    private final AuthorRepository authorRepository;
    private final EbookRepository ebookRepository;

    public EbookServiceImpl(CustomMapper mapper, AuthorRepository authorRepository, EbookRepository ebookRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.ebookRepository = ebookRepository;
    }

    @Override
    public EbookDto createEbook(EbookDto ebookDto) {
        Ebook eBookToCreate = mapToEntity(ebookDto);
        Author author = authorRepository.findById(ebookDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Ebook", "id", ebookDto.getAuthorId()));
        eBookToCreate.setAuthor(author);
        return mapToDTO(ebookRepository.save(eBookToCreate));
    }

    @Override
    public EbookResponse getAllEbooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<Ebook> content = ebookRepository.findAll(pageable);
        return getEbookResponse(content);
    }

    @Override
    public EbookDto getEbookById(Long id) {
        Ebook ebook = ebookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ebook", "id", id));
        return mapToDTO(ebook);
    }

    @Override
    public EbookDto updateEbookById(EbookDto ebookDto, Long ebookId) {
        Ebook eBookToUpdate = ebookRepository.findById(ebookId)
                .orElseThrow(() -> new ResourceNotFoundException("Ebook", "id", ebookId));
        Author author = authorRepository.findById(ebookDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", ebookDto.getAuthorId()));
        eBookToUpdate.setAuthor(author);
        eBookToUpdate.setISBN(ebookDto.getISBN());
        eBookToUpdate.setGenre(ebookDto.getGenre());
        eBookToUpdate.setSummary(ebookDto.getSummary());
        eBookToUpdate.setTitle(ebookDto.getTitle());
        eBookToUpdate.setTags(ebookDto.getTags());
        eBookToUpdate.setPublicationYear(ebookDto.getPublicationYear());
        if(ebookDto.getDownloadLink()!= null) {
            eBookToUpdate.setDownloadLink(ebookDto.getDownloadLink());
        }
        if(ebookDto.getReadingLink()!= null) {
            eBookToUpdate.setReadingLink(ebookDto.getReadingLink());
        }
        return mapToDTO(ebookRepository.save(eBookToUpdate));
    }

    @Override
    public void deleteEbookById(Long ebookId) {
        Ebook eBookToUpdate = ebookRepository.findById(ebookId)
                .orElseThrow(() -> new ResourceNotFoundException("Ebook", "id", ebookId));
        ebookRepository.delete(eBookToUpdate);
    }
    private Ebook mapToEntity(EbookDto ebookDto) {
        return mapper.map(ebookDto, Ebook.class);
    }

    private EbookDto mapToDTO(Ebook ebook) {
        return mapper.map(ebook, EbookDto.class);
    }

    private EbookResponse getEbookResponse(Page<Ebook> ebooks) {
        List<Ebook> listOfPosts = ebooks.getContent();
        List<EbookDto> content = listOfPosts.stream().map(this::mapToDTO).toList();
        EbookResponse ebookResponse = new EbookResponse();
        ebookResponse.setContent(content);
        ebookResponse.setPageNo(ebooks.getNumber());
        ebookResponse.setPageSize(ebooks.getSize());
        ebookResponse.setTotalElements(ebooks.getTotalElements());
        ebookResponse.setLast(ebooks.isLast());
        ebookResponse.setTotalPages(ebooks.getTotalPages());
        return ebookResponse;
    }
}
