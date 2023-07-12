package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.UserCloudHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Optional;

@RepositoryRestResource
@Tag(name = "User Cloud History Repository")
public interface UserCloudHistoryRepository extends JpaRepository<UserCloudHistory, Long> {

    @Operation(
            summary = "Get Cloud History By Book Id and User Id",
            description = "Search Cloud History By Book Id and User Id is used to get cloud history from the database"
    )
    Optional<UserCloudHistory> findByBookIdAndUserId(Long bookId, Long userId);

    @Operation(
            summary = "Get User's Cloud History By Read Time",
            description = "Search User's Cloud History By Read Time is used to get cloud history for a user by read time from the database"
    )
    Page<UserCloudHistory> findCloudHistoryByUserIdAndReadTime(Long userId, LocalDateTime readTime, Pageable pageable);

    @Operation(
            summary = "Get User's Cloud History By Download Time",
            description = "Search User's Cloud History By Download Time is used to get cloud history for a user by download time from the database"
    )
    Page<UserCloudHistory> findCloudHistoryByUserIdAndDownloadTime(Long userId, LocalDateTime downloadTime, Pageable pageable);

    @Operation(
            summary = "Get User's Cloud History By User Id",
            description = "Search User's Cloud History By User Id is used to get user's  cloud history from the database"
    )
    Page<UserCloudHistory> findAllByUserId(Long userId, Pageable pageable);

}
