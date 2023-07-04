package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
}
