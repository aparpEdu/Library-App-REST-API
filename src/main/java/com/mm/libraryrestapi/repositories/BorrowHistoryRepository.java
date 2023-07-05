package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.BorrowHistory;
import com.mm.libraryrestapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
    List<BorrowHistory> findByUser(User user);
    Page<BorrowHistory> findBorrowHistoryByUserId(Long userId, Pageable pageable);

    Page<BorrowHistory> findAll(Pageable pageable);

    List<BorrowHistory> findByUserId(Long userId);

    List<BorrowHistory> findByBookId(Long bookId);

    List<BorrowHistory> findByBorrowDate(LocalDate borrowDate);

    List<BorrowHistory> findByReturned(boolean returned);

}
