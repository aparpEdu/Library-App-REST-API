package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.CombinedBookResponse;
import com.mm.libraryrestapi.services.CombinedBookService;
import com.mm.libraryrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/combinedBooks")
@Tag(name = "GET API for Ebook and PaperBook Resource")
public class CombinedBookController {
    private final CombinedBookService combinedBookService;

    public CombinedBookController(CombinedBookService combinedBookService) {
        this.combinedBookService = combinedBookService;
    }
    @Operation(
            summary = "Get All Combined Books REST API",
            description = "Get All Combined Books REST API is used to get all of the ebooks and paper books from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping()
    public ResponseEntity<CombinedBookResponse> getAllCombinedBooks(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    )
    {
        return ResponseEntity.ok(combinedBookService.getAllCombinedBooks(pageNo,pageSize,sortBy,sortDir));
    }
    @Operation(
            summary = "Get Combined Books By Title REST API",
            description = "Get Combined Books REST API is used to get all of the ebooks and paper books by a title from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("title")
    public ResponseEntity<CombinedBookResponse> getCombinedBooksByTitle(
            @RequestParam String title,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        )
    {
        return ResponseEntity.ok(combinedBookService.findByTitle(title,pageNo,pageSize,sortBy,sortDir));
    }

    @Operation(
            summary = "Get Combined Books By Tags Name REST API",
            description = "Get Combined Books By Tags REST API is used to search ebooks and paper books by tags from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("tags")
    public ResponseEntity<CombinedBookResponse> getCombinedBooksByTags(
            @RequestParam String tags,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        )
    {
        return ResponseEntity.ok(combinedBookService.findByTagsContaining(tags,pageNo,pageSize,sortBy,sortDir));
    }

    @Operation(
            summary = "Get Combined Book By Summary REST API",
            description = "Get All Combined Books By Summary REST API is used to search ebooks and paper books by summary from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("summary")
    public ResponseEntity<CombinedBookResponse> getCombinedBooksBySummary(
            @RequestParam String summary,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        )
    {
        return ResponseEntity.ok(combinedBookService.findBySummaryContaining(summary,pageNo,pageSize,sortBy,sortDir));
    }

    @Operation(
            summary = "Get Combined Books By Genre REST API",
            description = "Get Combined Books By Genre REST API is used to search ebooks and paper books by genre from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("genre")
    public ResponseEntity<CombinedBookResponse> getCombinedBooksByGenre(
            @RequestParam String genre,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        )
    {
        return ResponseEntity.ok(combinedBookService.findByGenre(genre,pageNo,pageSize,sortBy,sortDir));
    }

    @Operation(
            summary = "Get Combined Books By Publication Year REST API",
            description = "Get Combined Books By Publication Year REST API is used to search ebooks and paperbooks by publication year from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("year")
    public ResponseEntity<CombinedBookResponse> getCombinedBooksByPublicationYear(
            @RequestParam int year,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        )
    {
        return ResponseEntity.ok(combinedBookService.findByPublicationYear(year,pageNo,pageSize,sortBy,sortDir));
    }

    @Operation(
            summary = "Get Combined Book By Author Id REST API",
            description = "Get Combined Books By Author Id REST API is used to search books by author id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("authorId")
    public ResponseEntity<CombinedBookResponse> getCombinedBooksByAuthorId(
            @RequestParam int authorId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        )
    {
        return ResponseEntity.ok(combinedBookService.findByAuthorId(authorId,pageNo,pageSize,sortBy,sortDir));
    }

}
