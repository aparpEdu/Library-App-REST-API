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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        //search for the current Logged User
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username",  userDetails.getUsername()));

        //check for pending returns
        List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.findByUser(user);
        borrowHistoryList.stream()
                .filter(record -> record.getReturnDate().isBefore(LocalDate.now()) && !record.isReturned())
                .findAny()
                .ifPresent(record -> {
                    throw new IllegalStateException("You have at least one book with a pending return");
                });

        //search book by id provided by the PathVariable
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("PaperBook", "id", bookId));

        //Create instance of borrow History
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
