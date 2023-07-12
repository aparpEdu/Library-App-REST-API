package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.dtos.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.response.UserCloudHistoryResponse;

import java.time.LocalDateTime;

public interface UserCloudHistoryService {
    UserCloudHistoryDto readABook(Long bookId, Long userId);

    UserCloudHistoryDto downloadABook(Long bookId, Long userId);

    UserCloudHistoryDto getBookByBookId(Long bookId, Long userId);

    UserCloudHistoryResponse getAllBooksByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    UserCloudHistoryResponse getCloudHistoryByReadTime(Long userId, LocalDateTime readTime, int pageNo, int pageSize, String sortBy, String sortDir);

    UserCloudHistoryResponse getCloudHistoryByDownloadTime(Long userId, LocalDateTime downloadTime, int pageNo, int pageSize, String sortBy, String sortDir);

}
