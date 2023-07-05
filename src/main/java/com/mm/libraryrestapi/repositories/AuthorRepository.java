package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    List<Author> findByCountry(String country);
}
