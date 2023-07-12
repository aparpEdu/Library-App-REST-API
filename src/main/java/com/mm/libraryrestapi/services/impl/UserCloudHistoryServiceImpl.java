package com.mm.libraryrestapi.services.impl;


import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.entity.UserCloudHistory;
import com.mm.libraryrestapi.exception.LibraryAPIException;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.dtos.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.response.UserCloudHistoryResponse;
import com.mm.libraryrestapi.repositories.BookRepository;
import com.mm.libraryrestapi.repositories.UserCloudHistoryRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserCloudHistoryServiceImpl implements UserCloudHistoryService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserCloudHistoryRepository userCloudHistoryRepository;
    private final CustomMapper mapper;

    public UserCloudHistoryServiceImpl(UserRepository userRepository, BookRepository bookRepository,
                                       UserCloudHistoryRepository userCloudHistoryRepository, CustomMapper mapper) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userCloudHistoryRepository = userCloudHistoryRepository;
        this.mapper = mapper;
    }

    @Override
    public UserCloudHistoryDto readABook(Long bookId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
        if(book.getDownloadLink() == null && book.getReadingLink() == null){
            throw new LibraryAPIException(HttpStatus.NOT_FOUND, "Book URL NOT FOUND");
        }
        UserCloudHistory userCloudHistory = new UserCloudHistory();
        userCloudHistory.setUser(user);
        userCloudHistory.setBook(book);
        userCloudHistory.setReadTime(LocalDateTime.now());
        userCloudHistory.setDownloadTime(null);
        return mapToDTO(userCloudHistoryRepository.save(userCloudHistory));
    }

    @Override
    public UserCloudHistoryDto downloadABook(Long bookId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
        if(book.getDownloadLink() == null && book.getReadingLink() == null){
            throw new LibraryAPIException(HttpStatus.NOT_FOUND, "Book URL NOT FOUND");
        }
        UserCloudHistory userCloudHistory = new UserCloudHistory();
        userCloudHistory.setUser(user);
        userCloudHistory.setBook(book);
        userCloudHistory.setReadTime(null);
        userCloudHistory.setDownloadTime(LocalDateTime.now());
        return mapToDTO(userCloudHistoryRepository.save(userCloudHistory));
    }

    @Override
    public UserCloudHistoryDto getBookByBookId(Long bookId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
        UserCloudHistory userCloudHistory = userCloudHistoryRepository
                .findByBookIdAndUserId(book.getId(), user.getId())
                .orElseThrow(() -> new LibraryAPIException(HttpStatus.BAD_REQUEST, "User and book don't match"));
        return mapToDTO(userCloudHistory);
    }

    @Override
    public UserCloudHistoryResponse getAllBooksByUserId(Long userId, int pageNo, int pageSize,
                                                        String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Page<UserCloudHistory> content = userCloudHistoryRepository.findAllByUserId(user.getId(), pageable);
        return getUserClodHistoryResponse(content);

   }

    @Override
    public UserCloudHistoryResponse getCloudHistoryByReadTime(Long userId, LocalDateTime readTime, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        Page<UserCloudHistory> content = userCloudHistoryRepository.findCloudHistoryByUserIdAndReadTime(user.getId(), readTime, pageable);
        return getUserClodHistoryResponse(content);
    }

    @Override
    public UserCloudHistoryResponse getCloudHistoryByDownloadTime(Long userId, LocalDateTime downloadTime, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        Page<UserCloudHistory> content = userCloudHistoryRepository.findCloudHistoryByUserIdAndDownloadTime(user.getId(), downloadTime, pageable);
        return getUserClodHistoryResponse(content);
    }

    private UserCloudHistoryResponse getUserClodHistoryResponse(Page<UserCloudHistory> userCloudHistories) {
        List<UserCloudHistory> userCloudHistory = userCloudHistories.getContent();
        List<UserCloudHistoryDto> content = userCloudHistory.stream().map(this::mapToDTO).toList();
        UserCloudHistoryResponse userCloudHistoryResponse = new UserCloudHistoryResponse();
        userCloudHistoryResponse.setContent(content);
        userCloudHistoryResponse.setPageNo(userCloudHistories.getNumber());
        userCloudHistoryResponse.setPageSize(userCloudHistories.getSize());
        userCloudHistoryResponse.setTotalElements(userCloudHistories.getTotalElements());
        userCloudHistoryResponse.setLast(userCloudHistories.isLast());
        userCloudHistoryResponse.setTotalPages(userCloudHistories.getTotalPages());
        return userCloudHistoryResponse;
    }

    private UserCloudHistoryDto mapToDTO(UserCloudHistory userCloudHistory) {
        return mapper.map(userCloudHistory, UserCloudHistoryDto.class);
    }
}
