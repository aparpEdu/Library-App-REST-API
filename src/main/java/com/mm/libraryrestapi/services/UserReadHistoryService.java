package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.UserReadHistoryDto;

public interface UserReadHistoryService {
    UserReadHistoryDto readABook(Long bookId, Long userId);
    UserReadHistoryDto getUserReadBook(Long bookId, Long userId);

}
