package com.mm.libraryrestapi.services.impl;


import com.mm.libraryrestapi.entity.Book;
import com.mm.libraryrestapi.entity.Ebook;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.entity.UserCloudHistory;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.BookDto;
import com.mm.libraryrestapi.payload.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.UserCloudHistoryResponse;
import com.mm.libraryrestapi.repositories.EbookRepository;
import com.mm.libraryrestapi.repositories.UserCloudHistoryRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.services.UserCloudHistoryService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public UserCloudHistoryResponse getAllReadBooksByUser(Long userId) {
        return null;
    }
    private UserCloudHistory mapToEntity(UserCloudHistoryDto userCloudHistoryDto) {
        return mapper.map(userCloudHistoryDto, UserCloudHistory.class);
    }

    private UserCloudHistoryDto mapToDTO(UserCloudHistory userCloudHistory) {
        return mapper.map(userCloudHistory, UserCloudHistoryDto.class);
    }
}
