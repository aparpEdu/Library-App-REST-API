package com.mm.libraryrestapi.services.impl;



import com.mm.libraryrestapi.entity.Ebook;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.entity.UserCloudHistory;
import com.mm.libraryrestapi.exception.LibraryAPIException;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.*;
import com.mm.libraryrestapi.repositories.EbookRepository;
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

import java.util.List;

@Service
public class UserCloudHistoryServiceImpl implements UserCloudHistoryService {

    private final UserRepository userRepository;
    private final EbookRepository ebookRepository;
    private final UserCloudHistoryRepository userCloudHistoryRepository;
    private final CustomMapper mapper;

    public UserCloudHistoryServiceImpl(UserRepository userRepository, EbookRepository ebookRepository,
                                       UserCloudHistoryRepository userCloudHistoryRepository, CustomMapper mapper) {
        this.userRepository = userRepository;
        this.ebookRepository = ebookRepository;
        this.userCloudHistoryRepository = userCloudHistoryRepository;
        this.mapper = mapper;
    }

    @Override
    public UserCloudHistoryDto readABook(Long bookId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        Ebook ebook = ebookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("book", "id", bookId));
        UserCloudHistory userCloudHistory = new UserCloudHistory();
        userCloudHistory.setUser(user);
        userCloudHistory.setEbook(ebook);
        return mapToDTO(userCloudHistoryRepository.save(userCloudHistory));
    }

    @Override
    public UserCloudHistoryDto getUserReadBook(Long bookId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        Ebook ebook = ebookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("book", "id", bookId));
        UserCloudHistory userCloudHistory = userCloudHistoryRepository
                .findByEbookIdAndUserId(ebook.getId(), user.getId())
                .orElseThrow(() -> new LibraryAPIException(HttpStatus.BAD_REQUEST, "User and book don't match"));
        return mapToDTO(userCloudHistory);
    }

    @Override
    public UserCloudHistoryResponse getAllReadBooksByUser(Long userId, int pageNo, int pageSize,
                                                          String sortBy, String sortDir) {
        Sort sortDirection = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection);
        Page<UserCloudHistory> content = userCloudHistoryRepository.findAllByUserId(userId, pageable);
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
