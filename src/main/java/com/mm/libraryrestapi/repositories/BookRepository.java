package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
