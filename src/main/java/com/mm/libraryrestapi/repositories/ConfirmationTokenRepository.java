package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.ConfirmationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Tag(name = "Confirmation Token Repository")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    @Operation(
            summary = "Get Confirmation Token By Token",
            description = "Search Confirmation Token By Token is used to get confirmation token from the database"
    )
    Optional<ConfirmationToken> findByToken(String token);

    @Operation(
            summary = "Update Confirmation Token's Confirmed Date",
            description = "Update Confirmation Token's Confirmed Date is used to update confirmation token's confirmed date"
    )
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
