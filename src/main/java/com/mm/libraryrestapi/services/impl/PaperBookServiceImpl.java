package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Author;
import com.mm.libraryrestapi.entity.PaperBook;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.PaperBookDto;
import com.mm.libraryrestapi.payload.PaperBookResponse;
import com.mm.libraryrestapi.repositories.AuthorRepository;
import com.mm.libraryrestapi.repositories.PaperBookRepository;
import com.mm.libraryrestapi.services.PaperBookService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaperBookServiceImpl implements PaperBookService {

    private final CustomMapper mapper;
    private final AuthorRepository authorRepository;
    private final PaperBookRepository paperBookRepository;

    public PaperBookServiceImpl(CustomMapper mapper, AuthorRepository authorRepository, PaperBookRepository paperBookRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.paperBookRepository = paperBookRepository;
    }

    @Override
    public PaperBookDto createBook(PaperBookDto paperBookDto) {
        PaperBook bookToCreate = mapToEntity(paperBookDto);
        Author author = authorRepository.findById(paperBookDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", paperBookDto.getAuthorId()));
        bookToCreate.setAuthor(author);
        return mapToDTO(paperBookRepository.save(bookToCreate));
    }

    @Override
    public PaperBookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<PaperBook> content = paperBookRepository.findAll(pageable);
        return getBookResponse(content);
    }

    @Override
    public PaperBookDto getBookById(Long id) {
        PaperBook book = paperBookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ebook", "id", id));
        return mapToDTO(book);
    }

    @Override
    public PaperBookDto updateBookById(PaperBookDto paperBookDto,Long paperBookId) {
        PaperBook bookToUpdate = paperBookRepository.findById(paperBookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", paperBookId));
        Author author = authorRepository.findById(paperBookDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", paperBookDto.getAuthorId()));
        bookToUpdate.setAuthor(author);
        bookToUpdate.setISBN(paperBookDto.getISBN());
        bookToUpdate.setGenre(paperBookDto.getGenre());
        bookToUpdate.setSummary(paperBookDto.getSummary());
        bookToUpdate.setTitle(paperBookDto.getTitle());
        bookToUpdate.setTags(paperBookDto.getTags());
        bookToUpdate.setPublicationYear(paperBookDto.getPublicationYear());
        bookToUpdate.setAvailableCopies(paperBookDto.getAvailableCopies());
        bookToUpdate.setTotalCopies(paperBookDto.getTotalCopies());
        return mapToDTO(paperBookRepository.save(bookToUpdate));
    }


    @Override
    public void deleteBookById(Long id) {
        PaperBook bookToUpdate = paperBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        paperBookRepository.delete(bookToUpdate);
    }

    private PaperBookResponse getBookResponse(Page<PaperBook> ebooks) {
        List<PaperBook> listOfPosts = ebooks.getContent();
        List<PaperBookDto> content = listOfPosts.stream().map(this::mapToDTO).toList();
        PaperBookResponse paperBookResponse = new PaperBookResponse();
        paperBookResponse.setContent(content);
        paperBookResponse.setPageNo(ebooks.getNumber());
        paperBookResponse.setPageSize(ebooks.getSize());
        paperBookResponse.setTotalElements(ebooks.getTotalElements());
        paperBookResponse.setLast(ebooks.isLast());
        paperBookResponse.setTotalPages(ebooks.getTotalPages());
        return paperBookResponse;
    }
    private PaperBook mapToEntity(PaperBookDto paperBookDto) {
        return mapper.map(paperBookDto, PaperBook.class);
    }

    private PaperBookDto mapToDTO(PaperBook book) {
        return mapper.map(book, PaperBookDto.class);
    }
}
