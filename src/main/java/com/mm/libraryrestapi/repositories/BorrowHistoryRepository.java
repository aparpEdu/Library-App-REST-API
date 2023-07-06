package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
@Tag(name = "Borrow History Repository")
public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {

    @Operation(
            summary = "Get Borrow History By User",
            description = "Search Borrow History By User is used to get borrow history for a user from the database"
    )
    List<BorrowHistory> findByUser(User user);

    @Operation(
            summary = "Get Borrow History By User",
            description = "Search Borrow History By User is used to get borrow history for a user from the database"
    )
    Page<BorrowHistory> findBorrowHistoryByUserId(Long userId, Pageable pageable);

    @Operation(
            summary = "Get Borrow History",
            description = "Search Borrow History is used to get borrow history from the database"
    )
    Page<BorrowHistory> findAll(Pageable pageable);

    @Operation(
            summary = "Get Borrow History By User Id",
            description = "Search Borrow History By User Id is used to get borrow history for a user from the database"
    )
    List<BorrowHistory> findByUserId(Long userId);

    @Operation(
            summary = "Get Borrow History By Book Id",
            description = "Search Borrow History By Book Id is used to get borrow history for a user by book id from the database"
    )
    List<BorrowHistory> findByBookId(Long bookId);

    @Operation(
            summary = "Get Borrow History By Borrow Date",
            description = "Search Borrow History By Borrow Date is used to get borrow history for a user by borrow date from the database"
    )
    List<BorrowHistory> findByBorrowDate(LocalDate borrowDate);

    @Operation(
            summary = "Get Borrow History By Returned",
            description = "Search Borrow History By Returned is used to get borrow history for a user from the database if the book is returned"
    )
    List<BorrowHistory> findByReturned(boolean returned);

}
