//package com.mm.libraryrestapi.repositories;
//
//import com.mm.libraryrestapi.entity.Ebook;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource
//@Tag(name = "EBook Repository")
//public interface EbookRepository extends JpaRepository<Ebook, Long> {
//
//    @Operation(
//            summary = "Get EBooks By Title",
//            description = "Search EBook By Title is used to get a list of ebooks from the database"
//    )
//    Page<Ebook> findByTitleContaining(String title,Pageable pageable);
//
//    @Operation(
//            summary = "Get EBooks By Tags",
//            description = "Search EBooks By Tags is used to get ebooks from the database"
//    )
//    Page<Ebook> findAllByTagsContainingIgnoreCase(String tags, Pageable pageable);
//
////    @Operation(
////            summary = "Get EBooks By Summary",
////            description = "Search EBooks By Summary is used to get ebooks from the database"
////    )
////    List<Ebook> findBySummaryContaining(String summary);
//
//    @Operation(
//            summary = "Get EBooks By Genre",
//            description = "Search EBooks By Genre is used to get ebooks from the database"
//    )
//    Page<Ebook> findAllByGenreContainingIgnoreCase(String genre, Pageable pageable);
//
//    @Operation(
//            summary = "Get EBooks By Publication Year",
//            description = "Search EBooks By Publication Year is used to get ebooks from the database"
//    )
//    Page<Ebook> findAllByPublicationYear(int publicationYear, Pageable pageable);
//
//    @Operation(
//            summary = "Get EBooks By Author  name",
//            description = "Search EBook By Author name is used to get ebooks from the database"
//    )
//    Page<Ebook> findAllByAuthor_FirstNameIgnoreCaseOrAuthor_LastNameIgnoreCase(String firstName, String lastName, Pageable pageable);
//
//}
