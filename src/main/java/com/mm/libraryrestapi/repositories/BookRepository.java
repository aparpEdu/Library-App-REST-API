package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByTagsContaining(String tags);

    List<Book> findBySummaryContaining(String summary);

    List<Book> findByGenre(String genre);

    List<Book> findByPublicationYear(int publicationYear);

    List<Book> findByAuthorId(int authorId);
}
