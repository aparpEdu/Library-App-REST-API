//package com.mm.libraryrestapi.services.impl;
//
//import com.mm.libraryrestapi.entity.Book;
//import com.mm.libraryrestapi.entity.CombinedBook;
//import com.mm.libraryrestapi.entity.Ebook;
//import com.mm.libraryrestapi.payload.dtos.CombinedBookDto;
//import com.mm.libraryrestapi.payload.response.CombinedBookResponse;
//import com.mm.libraryrestapi.repositories.BookRepository;
//import com.mm.libraryrestapi.repositories.EbookRepository;
//import com.mm.libraryrestapi.services.CombinedBookService;
//import com.mm.libraryrestapi.utils.CustomMapper;
//import org.springframework.data.domain.*;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CombinedBookServiceImpl implements CombinedBookService {
//    private final CustomMapper mapper;
//    private final BookRepository bookRepository;
//    private final EbookRepository ebookRepository;
//
//    public CombinedBookServiceImpl(CustomMapper mapper, BookRepository bookRepository, EbookRepository ebookRepository) {
//        this.mapper = mapper;
//        this.bookRepository = bookRepository;
//        this.ebookRepository = ebookRepository;
//    }
//
//    @Override
//    public CombinedBookResponse getAllCombinedBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        List<Book> books = bookRepository.findAll();
//        List<Ebook> ebooks = ebookRepository.findAll();
//        List<CombinedBook> combinedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            combinedBooks.add(mapToCombinedBook(book));
//        }
//        for (Ebook ebook : ebooks) {
//            combinedBooks.add(mapToCombinedBook(ebook));
//        }
//
//        Page<CombinedBook> content = paginate(combinedBooks, pageNo, pageSize, sortDirection);
//
//        return getCombinedBookResponse(content);
//    }
//
//    @Override
//    public CombinedBookResponse findByAuthorFullName(String firstName,String lastName, int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
//
//        List<Book> books = bookRepository.findAllByAuthor_FirstNameIgnoreCaseOrAuthor_LastNameIgnoreCase(firstName,lastName,pageable).getContent();
//        List<Ebook> ebooks = ebookRepository.findAllByAuthor_FirstNameIgnoreCaseOrAuthor_LastNameIgnoreCase(firstName, lastName,pageable).getContent();
//        List<CombinedBook> combinedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            combinedBooks.add(mapToCombinedBook(book));
//        }
//        for (Ebook ebook : ebooks) {
//            combinedBooks.add(mapToCombinedBook(ebook));
//        }
//
//        Page<CombinedBook> content = paginate(combinedBooks, pageNo, pageSize, sortDirection);
//
//        return getCombinedBookResponse(content);
//    }
//
//    @Override
//    public CombinedBookResponse findByPublicationYear(int publicationYear, int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
//
//        List<Book> books = bookRepository.findAllByPublicationYear(publicationYear,pageable).getContent();
//        List<Ebook> ebooks = ebookRepository.findAllByPublicationYear(publicationYear, pageable).getContent();
//        List<CombinedBook> combinedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            combinedBooks.add(mapToCombinedBook(book));
//        }
//        for (Ebook ebook : ebooks) {
//            combinedBooks.add(mapToCombinedBook(ebook));
//        }
//
//        Page<CombinedBook> content = paginate(combinedBooks, pageNo, pageSize, sortDirection);
//
//        return getCombinedBookResponse(content);
//    }
//
//    @Override
//    public CombinedBookResponse findByGenre(String genre, int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
//
//        List<Book> books = bookRepository.findAllByGenreContainingIgnoreCase(genre,pageable).getContent();
//        List<Ebook> ebooks = ebookRepository.findAllByGenreContainingIgnoreCase(genre,pageable).getContent();
//        List<CombinedBook> combinedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            combinedBooks.add(mapToCombinedBook(book));
//        }
//        for (Ebook ebook : ebooks) {
//            combinedBooks.add(mapToCombinedBook(ebook));
//        }
//
//        Page<CombinedBook> content = paginate(combinedBooks, pageNo, pageSize, sortDirection);
//
//        return getCombinedBookResponse(content);
//    }
//
//
//    @Override
//    public CombinedBookResponse findByTagsContaining(String tags, int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
//
//        List<Book> books = bookRepository.findAllByTagsContainingIgnoreCase(tags,pageable).getContent();
//        List<Ebook> ebooks = ebookRepository.findAllByTagsContainingIgnoreCase(tags,pageable).getContent();
//        List<CombinedBook> combinedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            combinedBooks.add(mapToCombinedBook(book));
//        }
//        for (Ebook ebook : ebooks) {
//            combinedBooks.add(mapToCombinedBook(ebook));
//        }
//
//        Page<CombinedBook> content = paginate(combinedBooks, pageNo, pageSize, sortDirection);
//
//        return getCombinedBookResponse(content);
//    }
//
//    @Override
//    public CombinedBookResponse findByTitle(String title, int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
//
//        List<Book> books = bookRepository.findByTitleContaining(title,pageable).getContent();
//        List<Ebook> ebooks = ebookRepository.findByTitleContaining(title, pageable).getContent();
//        List<CombinedBook> combinedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            combinedBooks.add(mapToCombinedBook(book));
//        }
//        for (Ebook ebook : ebooks) {
//            combinedBooks.add(mapToCombinedBook(ebook));
//        }
//
//        Page<CombinedBook> content = paginate(combinedBooks, pageNo, pageSize, sortDirection);
//
//        return getCombinedBookResponse(content);
//    }
//
//    private CombinedBookResponse getCombinedBookResponse(Page<CombinedBook> combinedBooks) {
//        List<CombinedBook> listOfPosts = combinedBooks.getContent();
//        List<CombinedBookDto> content = listOfPosts.stream().map(this::mapToDTO).toList();
//        CombinedBookResponse combinedBookResponse = new CombinedBookResponse();
//        combinedBookResponse.setContent(content);
//        combinedBookResponse.setPageNo(combinedBooks.getNumber());
//        combinedBookResponse.setPageSize(combinedBooks.getSize());
//        combinedBookResponse.setTotalElements(combinedBooks.getTotalElements());
//        combinedBookResponse.setLast(combinedBooks.isLast());
//        combinedBookResponse.setTotalPages(combinedBooks.getTotalPages());
//        return combinedBookResponse;
//    }
//    private Page<CombinedBook> paginate(List<CombinedBook> combinedBooks, int pageNo, int pageSize, Sort sortDirection) {
//        int totalItems = combinedBooks.size();
//
//        // Perform manual pagination on the list of books
//        int startIndex = pageNo * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalItems);
//        List<CombinedBook> pagedBooks = combinedBooks.subList(startIndex, endIndex);
//
//        // Create a Page object manually with the paged books
//        return new PageImpl<>(pagedBooks, PageRequest.of(pageNo, pageSize, sortDirection), totalItems);
//    }
//
//
//    private CombinedBookDto mapToDTO(CombinedBook combinedBook) {
//        return mapper.map(combinedBook, CombinedBookDto.class);
//    }
//
//    private CombinedBook mapToCombinedBook(Book book) {
//        CombinedBook combinedBook = new CombinedBook();
//        combinedBook.setId(book.getId());
//        combinedBook.setTitle(book.getTitle());
//        combinedBook.setTags(book.getTags());
//        combinedBook.setSummary(book.getSummary());
//        combinedBook.setISBN(book.getISBN());
//        combinedBook.setGenre(book.getGenre());
//        combinedBook.setPublicationYear(book.getPublicationYear());
//        combinedBook.setAuthor(book.getAuthor());
//        combinedBook.setAvailableCopies(book.getAvailableCopies());
//        combinedBook.setTotalCopies(book.getTotalCopies());
//        combinedBook.setBookType("book");
//        return combinedBook;
//    }
//
//    private CombinedBook mapToCombinedBook(Ebook ebook) {
//        CombinedBook combinedBook = new CombinedBook();
//        combinedBook.setId(ebook.getId());
//        combinedBook.setTitle(ebook.getTitle());
//        combinedBook.setTags(ebook.getTags());
//        combinedBook.setSummary(ebook.getSummary());
//        combinedBook.setISBN(ebook.getISBN());
//        combinedBook.setGenre(ebook.getGenre());
//        combinedBook.setPublicationYear(ebook.getPublicationYear());
//        combinedBook.setAuthor(ebook.getAuthor());
//        combinedBook.setDownloadLink(ebook.getDownloadLink());
//        combinedBook.setReadingLink(ebook.getReadingLink());
//        combinedBook.setBookType("ebook");
//        return combinedBook;
//    }
//}
