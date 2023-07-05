package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.entity.PaperBook;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.BorrowHistoryDto;
import com.mm.libraryrestapi.repositories.BorrowHistoryRepository;
import com.mm.libraryrestapi.repositories.PaperBookRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.services.BorrowPaperBookService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowPaperBookServiceImpl implements BorrowPaperBookService {
    private final CustomMapper mapper;
    private final BorrowHistoryRepository borrowHistoryRepository;

    private final UserRepository userRepository;
    private final PaperBookRepository paperBookRepository;

    public BorrowPaperBookServiceImpl(CustomMapper mapper, BorrowHistoryRepository borrowHistoryRepository, UserRepository userRepository, PaperBookRepository paperBookRepository) {
        this.mapper = mapper;
        this.borrowHistoryRepository = borrowHistoryRepository;
        this.userRepository = userRepository;
        this.paperBookRepository = paperBookRepository;
    }


    @Override
    public BorrowHistoryDto borrowPaperBook(BorrowHistoryDto borrowHistoryDto) {
        User user = userRepository.findById(borrowHistoryDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", borrowHistoryDto.getUserId()));
        PaperBook paperBook = paperBookRepository.findById(borrowHistoryDto.getPaperBookId())
                .orElseThrow(() -> new ResourceNotFoundException("PaperBook", "id", borrowHistoryDto.getPaperBookId()));

        BorrowHistory borrowHistoryToCreate = mapToEntity(borrowHistoryDto);

        borrowHistoryToCreate.setPaperBook(paperBook);
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
