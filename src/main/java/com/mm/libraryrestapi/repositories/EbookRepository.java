package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EbookRepository extends JpaRepository<Ebook, Long> {
}
