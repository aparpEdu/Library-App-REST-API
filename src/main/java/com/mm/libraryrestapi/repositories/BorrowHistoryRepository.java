package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
}
