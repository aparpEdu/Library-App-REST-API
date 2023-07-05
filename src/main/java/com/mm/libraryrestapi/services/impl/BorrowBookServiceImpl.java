package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.services.BorrowBookService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    private final CustomMapper mapper;
    private final BorrowHistoryRepository borrowHistoryRepository;

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowBookServiceImpl(CustomMapper mapper, BorrowHistoryRepository borrowHistoryRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.mapper = mapper;
        this.borrowHistoryRepository = borrowHistoryRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public BorrowHistoryDto borrowBook(Long bookId, BorrowHistoryDto borrowHistoryDto) {
        User user = userRepository.findById(borrowHistoryDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", borrowHistoryDto.getUserId()));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("PaperBook", "id", bookId));

        BorrowHistory borrowHistoryToCreate = mapToEntity(borrowHistoryDto);

        borrowHistoryToCreate.setBook(book);
        borrowHistoryToCreate.setUser(user);
        borrowHistoryToCreate.setBorrowDate(LocalDate.now());
        borrowHistoryToCreate.setReturnDate(LocalDate.now().plusDays(7));
        borrowHistoryToCreate.setReturned(false);

        return mapToDTO(borrowHistoryRepository.save(borrowHistoryToCreate));

    }

    private BorrowHistory mapToEntity(BorrowHistoryDto borrowHistoryDto) {
        return mapper.map(borrowHistoryDto, BorrowHistory.class);
    }

    private BorrowHistoryDto mapToDTO(BorrowHistory borrowHistory) {
        return mapper.map(borrowHistory, BorrowHistoryDto.class);
    }
}
