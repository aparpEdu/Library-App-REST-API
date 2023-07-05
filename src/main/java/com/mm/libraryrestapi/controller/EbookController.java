package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Ebook;
import com.mm.libraryrestapi.payload.EbookDto;
import com.mm.libraryrestapi.payload.EbookResponse;
import com.mm.libraryrestapi.repositories.EbookRepository;
import com.mm.libraryrestapi.services.EbookService;
import com.mm.libraryrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ebooks")
@Tag(name = "CRUD REST APIs for EBook Resource")
public class EbookController {
    private final EbookService ebookService;
    private final EbookRepository ebookRepository;

    public EbookController(EbookService ebookService, EbookRepository ebookRepository) {
        this.ebookService = ebookService;
        this.ebookRepository = ebookRepository;
    }

    @Operation(
            summary = "Get EBook By Id REST API",
            description = "Get EBook By Id REST API is used to get a single ebook from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{ebookId}")
    public ResponseEntity<EbookDto> getEbookById(@PathVariable Long ebookId) {
        return ResponseEntity.ok(ebookService.getEbookById(ebookId));
    }

    @Operation(
            summary = "Create EBook REST API",
            description = "Create EBook REST API is used to save an ebook into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<EbookDto> createEbook(@Valid @RequestBody EbookDto ebookDto) {
        return new ResponseEntity<>(ebookService.createEbook(ebookDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All EBooks REST API",
            description = "Get All EBooks REST API is used to fetch all ebooks from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<EbookResponse> getAllEbooks
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(ebookService.getAllEbooks(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update EBook By Id REST API",
            description = "Update Ebook By Id REST API is used to update an ebook by a particular book id in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{ebookId}")
    public ResponseEntity<EbookDto> updateEbookById(@Valid @RequestBody EbookDto ebookDto, @PathVariable Long ebookId) {
        return ResponseEntity.ok(ebookService.updateEbookById(ebookDto, ebookId));
    }

    @Operation(
            summary = "Delete EBook By Id REST API",
            description = "Delete EBook By Id REST API is used to delete a particular ebook from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{ebookId}")
    public ResponseEntity<String> deleteEbookById(@PathVariable Long ebookId) {
        ebookService.deleteEbookById(ebookId);
        return ResponseEntity.ok("Ebook was successfully deleted");
    }

    @Operation(
            summary = "Get EBook By Title REST API",
            description = "Search EBook By Title REST API is used to search for ebook by title in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("title")
    public ResponseEntity<Ebook> getEBookByTitle(@RequestParam String title) {
        return ResponseEntity.ok(ebookRepository.findByTitle(title));
    }

    @Operation(
            summary = "Get EBook By Tags REST API",
            description = "Search Ebook By Tags REST API is used to search for ebooks by tags in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("tags")
    public ResponseEntity<List<Ebook>> getEBookByTags(@RequestParam String tags) {
        return ResponseEntity.ok(ebookRepository.findByTagsContaining(tags));
    }

    @Operation(
            summary = "Get EBook By Summary REST API",
            description = "Search EBook By Summary REST API is used to search for ebooks by summary in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("summary")
    public ResponseEntity<List<Ebook>> getEBookBySummary(@RequestParam String summary) {
        return ResponseEntity.ok(ebookRepository.findBySummaryContaining(summary));
    }

    @Operation(
            summary = "Get EBook By Genre REST API",
            description = "Search EBook By Genre REST API is used to search ebooks by genre in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("genre")
    public ResponseEntity<List<Ebook>> getEBookByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(ebookRepository.findByGenre(genre));
    }

    @Operation(
            summary = "Get EBook By Publication Year REST API",
            description = "Search EBook By Publication Year REST API is used to search ebooks by publication year in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("year")
    public ResponseEntity<List<Ebook>> getEBookByPublicationYear(@RequestParam int publicationYear) {
        return ResponseEntity.ok(ebookRepository.findByPublicationYear(publicationYear));
    }

    @Operation(
            summary = "Get EBook By Author Id REST API",
            description = "Search EBook By Author Id REST API is used to search ebooks by author id in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("author")
    public ResponseEntity<List<Ebook>> getEBookByAuthorId(@RequestParam int authorId) {
        return ResponseEntity.ok(ebookRepository.findByAuthorId(authorId));
    }
}
