package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.PaperBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperbookRepository extends JpaRepository<PaperBook, Long> {
}
