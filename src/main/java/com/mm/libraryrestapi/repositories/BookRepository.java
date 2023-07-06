package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
@Tag(name = "Book Repository")
public interface BookRepository extends JpaRepository<Book, Long> {

    @Operation(
            summary = "Get Book By Title",
            description = "Search Book By Title is used to get a single book from the database"
    )
    Book findByTitle(String title);

    @Operation(
            summary = "Get Books By Tags",
            description = "Search Books By Tags is used to get books from the database"
    )
    List<Book> findByTagsContaining(String tags);

    @Operation(
            summary = "Get Books By Summary",
            description = "Search Books By Summary is used to get books from the database"
    )
    List<Book> findBySummaryContaining(String summary);

    @Operation(
            summary = "Get Books By Genre",
            description = "Search Books By Genre is used to get books from the database"
    )
    List<Book> findByGenre(String genre);

    @Operation(
            summary = "Get Books By Publication Year",
            description = "Search Books By Publication Year is used to get books from the database"
    )
    List<Book> findByPublicationYear(int publicationYear);

    @Operation(
            summary = "Get Books By Author Id",
            description = "Search Books By Author Id is used to get books from the database"
    )
    List<Book> findByAuthorId(int authorId);
}
