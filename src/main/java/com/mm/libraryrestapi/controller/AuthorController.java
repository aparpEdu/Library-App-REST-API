package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Author;
import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;
import com.mm.libraryrestapi.repositories.AuthorRepository;
import com.mm.libraryrestapi.services.AuthorService;
import com.mm.libraryrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/authors")
@RestController
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long authorId) {
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author Successfully deleted");
    }

    @GetMapping("")
    public ResponseEntity<AuthorResponse> getAllAuthors(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(authorService.getAllAuthors(pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{authorId}")
    public ResponseEntity<AuthorDto> updateAuthorById(@PathVariable Long authorId,
                                                      @Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.updateAuthorById(authorDto, authorId));
    }

    @GetMapping("/firstName")
    public ResponseEntity<List<Author>> getAuthorsByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(authorRepository.findByFirstName(firstName));
    }

    @GetMapping("/lastName")
    public ResponseEntity<List<Author>> getAuthorsByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(authorRepository.findByLastName(lastName));
    }

    @GetMapping("/country")
    public ResponseEntity<List<Author>> getAuthorsByCountry(@RequestParam String country) {
        return ResponseEntity.ok(authorRepository.findByCountry(country));
    }
}
