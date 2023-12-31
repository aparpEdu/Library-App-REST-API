package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.exception.LibraryAPIException;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.payload.BorrowHistoryResponse;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.services.BookService;
import com.mm.libraryrestapi.services.BorrowBookService;
import com.mm.libraryrestapi.utils.AppConstants;
import com.mm.libraryrestapi.utils.CustomMapper;
import com.mm.libraryrestapi.utils.ErrorMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    private final CustomMapper mapper;
    private final BorrowHistoryRepository borrowHistoryRepository;

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public BorrowBookServiceImpl(CustomMapper mapper, BorrowHistoryRepository borrowHistoryRepository, UserRepository userRepository, BookRepository bookRepository, BookService bookService) {
        this.mapper = mapper;
        this.borrowHistoryRepository = borrowHistoryRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }


    @Override
    public BorrowHistoryDto borrowBook(Long bookId) {
        //search for the current Logged User
        User loggedUser = getLoggedUser();

        //check for pending returns
        List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.findByUser(loggedUser);
        borrowHistoryList.stream()
                .filter(record -> record.getReturnDate().isBefore(LocalDate.now()) && !record.isReturned())
                .findAny()
                .ifPresent(record -> {
                    throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.PENDING_RETURN);
                });

        //search book by id provided by the PathVariable
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("PaperBook", "id", bookId));

        //check if there are available books
        if(book.getAvailableCopies()<1)
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.NO_BOOKS_AVAILABLE);

        //Create instance of borrow History
        BorrowHistory borrowHistoryToCreate = new BorrowHistory();

        borrowHistoryToCreate.setBook(book);
        borrowHistoryToCreate.setUser(loggedUser);
        borrowHistoryToCreate.setBorrowDate(LocalDate.now());
        borrowHistoryToCreate.setReturnDate(LocalDate.now().plusDays(AppConstants.DAYS_TO_RETURN));
        borrowHistoryToCreate.setReturned(false);

        //Update the available books by subtracting one
        bookService.updateNumberOfBooksAfterBorrowing(bookId);

        return mapToDTO(borrowHistoryRepository.save(borrowHistoryToCreate));
    }

    @Override
    public BorrowHistoryDto postponeReturnDate(Long borrowHistoryId, Long days) {
        // Find the existing BorrowHistory record by ID
        BorrowHistory borrowHistoryToUpdate = borrowHistoryRepository.findById(borrowHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow History","borrowHistoryId",borrowHistoryId));

        //check if the borrowHistory correspond to the user
        User loggedUser= getLoggedUser();
        if(!Objects.equals(borrowHistoryToUpdate.getUser().getId(), loggedUser.getId()))
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_USER);

        //check if the book was already return
        if(borrowHistoryToUpdate.isReturned())
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.BOOK_ALREADY_RETURNED);
        //check if we are adding negative or zero days
        if(days<1)
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_POSTPONEMENT_DAYS);

        // Check if the postpone date isn't after the max postponement date allowed
        if(DAYS.between(borrowHistoryToUpdate.getBorrowDate(), borrowHistoryToUpdate.getReturnDate().plusDays(days)) > AppConstants.MAX_POSTPONEMENT_DAYS)
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.POSTPONE_DATE_LIMIT);

        //Update the postponement date
        borrowHistoryToUpdate.setReturnDate(borrowHistoryToUpdate.getReturnDate().plusDays(days));

        // Save the updated BorrowHistory record
        BorrowHistory updatedBorrowHistory = borrowHistoryRepository.save(borrowHistoryToUpdate);

        return mapToDTO(updatedBorrowHistory);
    }

    @Override
    public BorrowHistoryDto returnPaperBook(Long borrowHistoryId) {
        // Find the existing BorrowHistory record by ID
        BorrowHistory borrowHistoryToUpdate = borrowHistoryRepository.findById(borrowHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow History","borrowHistoryId",borrowHistoryId));

        //check if the borrowHistory correspond to the user
        User loggedUser= getLoggedUser();
        if(!Objects.equals(borrowHistoryToUpdate.getUser().getId(), loggedUser.getId()))
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_USER);

        //check if the book has not been returned yet
        if(borrowHistoryToUpdate.isReturned())
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.BOOK_ALREADY_RETURNED);

        //Update the return status
        borrowHistoryToUpdate.setReturned(true);

        // Update the available books by adding 1
        bookService.updateNumberOfBooksAfterReturning(borrowHistoryToUpdate.getBook().getId());

        return mapToDTO(borrowHistoryRepository.save(borrowHistoryToUpdate));    }

    @Override
    public BorrowHistoryResponse getAllBooksBorrowedByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        Page<BorrowHistory> content = borrowHistoryRepository.findBorrowHistoryByUserId(user.getId(), pageable);
        return getBorrowHistoryResponse(content);
    }

    @Override
    public BorrowHistoryResponse getAllBooksBorrowedByLoggedUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        User user = getLoggedUser();
        Page<BorrowHistory> content = borrowHistoryRepository.findBorrowHistoryByUserId(user.getId(), pageable);
        return getBorrowHistoryResponse(content);
    }

    @Override
    public BorrowHistoryResponse getAllBooksBorrowed(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<BorrowHistory> content = borrowHistoryRepository.findAll(pageable);
        return getBorrowHistoryResponse(content);
    }

    @Override
    public BorrowHistoryResponse getBorrowHistoryByUserId(Long userId,  int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<BorrowHistory> content = borrowHistoryRepository.getBorrowHistoryByUserId(userId, pageable);
        return getBorrowHistoryResponse(content);
    }

    @Override
    public BorrowHistoryResponse getBorrowHistoryByBookId(Long bookId,  int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<BorrowHistory> content =  borrowHistoryRepository.getBorrowHistoryByBookId(bookId, pageable);
        return getBorrowHistoryResponse(content);
    }

    @Override
    public BorrowHistoryResponse getBorrowHistoryByBorrowDate(LocalDate borrowDate, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<BorrowHistory> content = borrowHistoryRepository.getBorrowHistoryByBorrowDate(borrowDate, pageable);
        return getBorrowHistoryResponse(content);
    }

    @Override
    public BorrowHistoryResponse getBorrowHistoryByReturned(boolean returned, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<BorrowHistory> content = borrowHistoryRepository.getBorrowHistoryByReturned(returned, pageable);
        return getBorrowHistoryResponse(content);
    }


    private BorrowHistoryDto mapToDTO(BorrowHistory borrowHistory) {
        return mapper.map(borrowHistory, BorrowHistoryDto.class);
    }

    private User getLoggedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userRepository.getUserByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username",  userDetails.getUsername()));
    }

    private BorrowHistoryResponse getBorrowHistoryResponse(Page<BorrowHistory> borrowHistoryPage) {
        List<BorrowHistory> borrowHistoryList = borrowHistoryPage.getContent();
        List<BorrowHistoryDto> content = borrowHistoryList.stream().map(this::mapToDTO).toList();
        BorrowHistoryResponse borrowHistoryResponse = new BorrowHistoryResponse();
        borrowHistoryResponse.setContent(content);
        borrowHistoryResponse.setPageNo(borrowHistoryPage.getNumber());
        borrowHistoryResponse.setPageSize(borrowHistoryPage.getSize());
        borrowHistoryResponse.setTotalElements(borrowHistoryPage.getTotalElements());
        borrowHistoryResponse.setLast(borrowHistoryPage.isLast());
        borrowHistoryResponse.setTotalPages(borrowHistoryPage.getTotalPages());
        return borrowHistoryResponse;
    }
}
