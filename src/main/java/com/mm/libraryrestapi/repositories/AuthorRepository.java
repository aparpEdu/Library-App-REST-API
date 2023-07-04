package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
