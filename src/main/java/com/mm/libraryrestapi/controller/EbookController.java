package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Ebook;
import com.mm.libraryrestapi.payload.EbookDto;
import com.mm.libraryrestapi.payload.EbookResponse;
import com.mm.libraryrestapi.repositories.EbookRepository;
import com.mm.libraryrestapi.services.EbookService;
import com.mm.libraryrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ebooks")
public class EbookController {
    private final EbookService ebookService;
    private final EbookRepository ebookRepository;

    public EbookController(EbookService ebookService, EbookRepository ebookRepository) {
        this.ebookService = ebookService;
        this.ebookRepository = ebookRepository;
    }

    @GetMapping("{ebookId}")
    public ResponseEntity<EbookDto> getEbookById(@PathVariable Long ebookId) {
        return ResponseEntity.ok(ebookService.getEbookById(ebookId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<EbookDto> createEbook(@Valid @RequestBody EbookDto ebookDto) {
        return new ResponseEntity<>(ebookService.createEbook(ebookDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<EbookResponse> getAllEbooks
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(ebookService.getAllEbooks(pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{ebookId}")
    public ResponseEntity<EbookDto> updateEbookById(@Valid @RequestBody EbookDto ebookDto, @PathVariable Long ebookId){
        return ResponseEntity.ok(ebookService.updateEbookById(ebookDto, ebookId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{ebookId}")
    public ResponseEntity<String> deleteEbookById(@PathVariable Long ebookId){
        ebookService.deleteEbookById(ebookId);
        return ResponseEntity.ok("Ebook was successfully deleted");
    }

    @GetMapping("title")
    public ResponseEntity<Ebook> getEBookByTitle(@RequestParam String title) {
        return ResponseEntity.ok(ebookRepository.findByTitle(title));
    }

    @GetMapping("tags")
    public ResponseEntity<List<Ebook>> getEBookByTags(@RequestParam String tags) {
        return ResponseEntity.ok(ebookRepository.findByTagsContaining(tags));
    }

    @GetMapping("summary")
    public ResponseEntity<List<Ebook>> getEBookBySummary(@RequestParam String summary) {
        return ResponseEntity.ok(ebookRepository.findBySummaryContaining(summary));
    }

    @GetMapping("genre")
    public ResponseEntity<List<Ebook>> getEBookByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(ebookRepository.findByGenre(genre));
    }

    @GetMapping("year")
    public ResponseEntity<List<Ebook>> getEBookByPublicationYear(@RequestParam int publicationYear) {
        return ResponseEntity.ok(ebookRepository.findByPublicationYear(publicationYear));
    }

    @GetMapping("author")
    public ResponseEntity<List<Ebook>> getEBookByAuthorId(@RequestParam int authorId) {
        return ResponseEntity.ok(ebookRepository.findByAuthorId(authorId));
    }
}
