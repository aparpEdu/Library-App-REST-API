package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<Ebook, Long> {
}
