package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Author;
import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.PaperBookResponse;
import com.mm.libraryrestapi.repositories.AuthorRepository;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.services.BookService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private final CustomMapper mapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(CustomMapper mapper, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book bookToCreate = mapToEntity(bookDto);
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookDto.getAuthorId()));
        bookToCreate.setAuthor(author);
        return mapToDTO(bookRepository.save(bookToCreate));
    }

    @Override
    public PaperBookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<Book> content = bookRepository.findAll(pageable);
        return getBookResponse(content);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ebook", "id", id));
        return mapToDTO(book);
    }

    @Override
    public BookDto updateBookById(BookDto bookDto, Long paperBookId) {
        Book bookToUpdate = bookRepository.findById(paperBookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", paperBookId));
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", bookDto.getAuthorId()));
        bookToUpdate.setAuthor(author);
        bookToUpdate.setISBN(bookDto.getISBN());
        bookToUpdate.setGenre(bookDto.getGenre());
        bookToUpdate.setSummary(bookDto.getSummary());
        bookToUpdate.setTitle(bookDto.getTitle());
        bookToUpdate.setTags(bookDto.getTags());
        bookToUpdate.setPublicationYear(bookDto.getPublicationYear());
        bookToUpdate.setAvailableCopies(bookDto.getAvailableCopies());
        bookToUpdate.setTotalCopies(bookDto.getTotalCopies());
        return mapToDTO(bookRepository.save(bookToUpdate));
    }


    @Override
    public void deleteBookById(Long id) {
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(bookToUpdate);
    }

    @Override
    public void updateAvailableBooks(Long bookId, int booksToAdd) {
        Book bookToUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        bookToUpdate.setAvailableCopies(bookToUpdate.getAvailableCopies()+booksToAdd);
    }

    private PaperBookResponse getBookResponse(Page<Book> ebooks) {
        List<Book> listOfPosts = ebooks.getContent();
        List<BookDto> content = listOfPosts.stream().map(this::mapToDTO).toList();
        PaperBookResponse paperBookResponse = new PaperBookResponse();
        paperBookResponse.setContent(content);
        paperBookResponse.setPageNo(ebooks.getNumber());
        paperBookResponse.setPageSize(ebooks.getSize());
        paperBookResponse.setTotalElements(ebooks.getTotalElements());
        paperBookResponse.setLast(ebooks.isLast());
        paperBookResponse.setTotalPages(ebooks.getTotalPages());
        return paperBookResponse;
    }
    private Book mapToEntity(BookDto bookDto) {
        return mapper.map(bookDto, Book.class);
    }

    private BookDto mapToDTO(Book book) {
        return mapper.map(book, BookDto.class);
    }
}
