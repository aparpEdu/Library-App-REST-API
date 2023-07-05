package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.UserCloudHistoryResponse;

public interface UserCloudHistoryService {
    UserCloudHistoryDto readABook(Long bookId, Long userId);
    UserCloudHistoryDto getUserReadBook(Long bookId, Long userId);
    UserCloudHistoryResponse getAllReadBooksByUser(Long userId);
}
