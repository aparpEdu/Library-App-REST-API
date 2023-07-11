package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.UserCloudHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@Tag(name = "User Cloud History Repository")
public interface UserCloudHistoryRepository extends JpaRepository<UserCloudHistory, Long> {

    @Operation(
            summary = "Get Cloud History By EBook Id and User Id",
            description = "Search Cloud History By EBook Id and User Id is used to get cloud history from the database"
    )
    Optional<UserCloudHistory> findByBookIdAndUserId(Long ebookId, Long userId);

    @Operation(
            summary = "Get Cloud Histories By User Id",
            description = "Search Cloud Histories By User Id is used to get cloud histories from the database"
    )
    Page<UserCloudHistory> findAllByUserId(Long userId, Pageable pageable);

}
