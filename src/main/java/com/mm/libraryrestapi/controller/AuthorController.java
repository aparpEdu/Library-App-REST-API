package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;
import com.mm.libraryrestapi.services.AuthorService;
import com.mm.libraryrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/authors")
@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto){
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId){
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long authorId){
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author Successfully deleted");
    }

    @GetMapping("")
    public ResponseEntity<AuthorResponse> getAllAuthors(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return  ResponseEntity.ok(authorService.getAllAuthors(pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("{authorId}")
    public ResponseEntity<AuthorDto> updateAuthorById(@PathVariable Long authorId,
                                                      @Valid @RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.updateAuthorById(authorDto, authorId));
    }
}
