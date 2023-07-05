package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.UserCloudHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserCloudHistoryRepository extends JpaRepository<UserCloudHistory, Long> {
    Optional<UserCloudHistory> findByEbookIdAndUserId(Long ebookId, Long userId);
    Page<UserCloudHistory> findAllByUserId(Long userId, Pageable pageable);

    List<UserCloudHistory> findByUserId(Long userId);

    List<UserCloudHistory> findByEbookId(Long ebookId);
}
