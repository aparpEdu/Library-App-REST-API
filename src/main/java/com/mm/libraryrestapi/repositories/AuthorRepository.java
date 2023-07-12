package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
@Tag(name = "Author Repository")
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Operation(
            summary = "Get Author By First Name",
            description = "Get Author By First Name is used to get a single author by first name from the database"
    )
    Page<Author> findByFirstName(String firstName, Pageable pageable);

    @Operation(
            summary = "Get Author By Last Name",
            description = "Get Author By Last Name is used to get a single author by last name from the database"
    )
    Page<Author> findByLastName(String lastName, Pageable pageable);

    @Operation(
            summary = "Get Author By Country",
            description = "Get Author By Country is used to get a single author by country from the database"
    )
    Page<Author> findByCountry(String country, Pageable pageable);
}
