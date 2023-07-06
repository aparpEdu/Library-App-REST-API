package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
@Tag(name = "Author Repository")
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Operation(
            summary = "Get Author By First Name",
            description = "Get Author By First Name is used to get a single author from the database"
    )
    List<Author> findByFirstName(String firstName);

    @Operation(
            summary = "Get Author By Last Name",
            description = "Get Author By Last Name is used to get a single author from the database"
    )
    List<Author> findByLastName(String lastName);

    @Operation(
            summary = "Get Author By Country",
            description = "Get Author By Country is used to get a single author from the database"
    )
    List<Author> findByCountry(String country);
}
