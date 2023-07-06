package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Ebook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
@Tag(name = "EBook Repository")
public interface EbookRepository extends JpaRepository<Ebook, Long> {

    @Operation(
            summary = "Get EBook By Title",
            description = "Search EBook By Title is used to get a single book from the database"
    )
    Ebook findByTitle(String title);

    @Operation(
            summary = "Get EBooks By Tags",
            description = "Search EBooks By Tags is used to get ebooks from the database"
    )
    List<Ebook> findByTagsContaining(String tags);

    @Operation(
            summary = "Get EBooks By Summary",
            description = "Search EBooks By Summary is used to get ebooks from the database"
    )
    List<Ebook> findBySummaryContaining(String summary);

    @Operation(
            summary = "Get EBooks By Genre",
            description = "Search EBooks By Genre is used to get ebooks from the database"
    )
    List<Ebook> findByGenre(String genre);

    @Operation(
            summary = "Get EBooks By Publication Year",
            description = "Search EBooks By Publication Year is used to get ebooks from the database"
    )
    List<Ebook> findByPublicationYear(int publicationYear);

    @Operation(
            summary = "Get EBooks By Author Id",
            description = "Search EBook By Author Id is used to get ebooks from the database"
    )
    List<Ebook> findByAuthorId(int authorId);
}
