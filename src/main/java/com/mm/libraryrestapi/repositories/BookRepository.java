package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
@Tag(name = "Book Repository")
public interface BookRepository extends JpaRepository<Book, Long> {

    @Operation(
            summary = "Get Books By Title",
            description = "Search Book By Title is used to get a list of books from the database"
    )
    Page<Book> findByTitleContaining(String title, Pageable pageable);

    @Operation(
            summary = "Get Books By Tags",
            description = "Search EBooks By Tags is used to get books from the database"
    )
    Page<Book> findAllByTagsContainingIgnoreCase(String tags, Pageable pageable);

//    @Operation(
//            summary = "Get EBooks By Summary",
//            description = "Search EBooks By Summary is used to get ebooks from the database"
//    )
//    List<Ebook> findBySummaryContaining(String summary);

    @Operation(
            summary = "Get Books By Genre",
            description = "Search Books By Genre is used to get books from the database"
    )
    Page<Book> findAllByGenreContainingIgnoreCase(String genre, Pageable pageable);

    @Operation(
            summary = "Get Books By Publication Year",
            description = "Search Books By Publication Year is used to get books from the database"
    )
    Page<Book> findAllByPublicationYear(int publicationYear, Pageable pageable);

    @Operation(
            summary = "Get Books By Author name",
            description = "Search Books By Author name is used to get books from the database"
    )
    Page<Book> findAllByAuthor_FirstNameIgnoreCaseOrAuthor_LastNameIgnoreCase(String firstName, String lastName, Pageable pageable);

}
