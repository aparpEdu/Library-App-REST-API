package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EbookRepository extends JpaRepository<Ebook, Long> {

    Ebook findByTitle(String title);

    List<Ebook> findByTagsContaining(String tags);

    List<Ebook> findBySummaryContaining(String summary);

    List<Ebook> findByGenre(String genre);

    List<Ebook> findByPublicationYear(int publicationYear);

    List<Ebook> findByAuthorId(int authorId);
}
