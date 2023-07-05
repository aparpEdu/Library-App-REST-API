package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.PaperBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaperBookRepository extends JpaRepository<PaperBook, Long> {
}
