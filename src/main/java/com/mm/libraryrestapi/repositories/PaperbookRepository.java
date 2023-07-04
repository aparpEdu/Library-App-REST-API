package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Paperbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperbookRepository extends JpaRepository<Paperbook, Long> {
}
