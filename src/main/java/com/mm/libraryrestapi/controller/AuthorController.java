package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.entity.Author;
import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;
import com.mm.libraryrestapi.repositories.AuthorRepository;
import com.mm.libraryrestapi.services.AuthorService;
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

@RequestMapping("/api/v1/authors")
@RestController
@Tag(name = "CRUD REST APIs for Author Resource")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @Operation(
            summary = "Create Author REST API",
            description = "Create Author REST API is used to save author into database"
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
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Author By Id REST API",
            description = "Get Author By Id REST API is used to get a single author from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @Operation(
            summary = "Delete Author REST API",
            description = "Delete Author REST API is used to delete author from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long authorId) {
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author Successfully deleted");
    }

    @Operation(
            summary = "Get All Authors REST API",
            description = "Get All Authors REST API is used to fetch all the authors from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<AuthorResponse> getAllAuthors(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(authorService.getAllAuthors(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update Author REST API",
            description = "Update Author REST API is used to update a particular author in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{authorId}")
    public ResponseEntity<AuthorDto> updateAuthorById(@PathVariable Long authorId,
                                                      @Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.updateAuthorById(authorDto, authorId));
    }

    @Operation(
            summary = "Get Author By First Name REST API",
            description = "Search Author By First Name REST API is used to search authors by first name from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/firstName")
    public ResponseEntity<List<Author>> getAuthorsByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(authorRepository.findByFirstName(firstName));
    }

    @Operation(
            summary = "Get Author By Last Name REST API",
            description = "Search Author By Last Name REST API is used to search authors by last name from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/lastName")
    public ResponseEntity<List<Author>> getAuthorsByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(authorRepository.findByLastName(lastName));
    }

    @Operation(
            summary = "Get Author By Country REST API",
            description = "Search Author By Country REST API is used to search authors by country from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/country")
    public ResponseEntity<List<Author>> getAuthorsByCountry(@RequestParam String country) {
        return ResponseEntity.ok(authorRepository.findByCountry(country));
    }
}
