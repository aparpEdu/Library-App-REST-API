package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.dtos.UserCloudHistoryDto;
import com.mm.libraryrestapi.payload.response.UserCloudHistoryResponse;

public interface UserCloudHistoryService {
    UserCloudHistoryDto readABook(Long bookId, Long userId);

    UserCloudHistoryDto getUserReadBook(Long bookId, Long userId);

    UserCloudHistoryResponse getAllReadBooksByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);
}
