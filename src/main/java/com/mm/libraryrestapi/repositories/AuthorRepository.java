package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
