package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.BookResponse;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelMapper mapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = mapToEntity(bookDto);
        Book newBook = bookRepository.save(book);
        return mapToDTO(newBook);
    }

    @Override
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);

        List<Book> bookList = books.getContent();

        List<BookDto> bookDtoList = bookList.stream().map(this::mapToDTO).collect(Collectors.toList());
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookDtoList(bookDtoList);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());
        return bookResponse;
    }

    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return mapToDTO(book);
    }

    @Override
    public BookDto updateBookById(BookDto bookDto, long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setISBN(bookDto.getISBN());
        book.setSummary(bookDto.getSummary());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setTags(book.getTags());

        Book updatedBook = bookRepository.save(book);
        return mapToDTO(updatedBook);
    }

    @Override
    public void deleteBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(book);
    }

    private Book mapToEntity(BookDto bookDto) {
        return mapper.map(bookDto, Book.class);
    }

    private BookDto mapToDTO(Book book) {
        return mapper.map(book, BookDto.class);
    }
}
