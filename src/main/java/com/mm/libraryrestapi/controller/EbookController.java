package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.EbookDto;
import com.mm.libraryrestapi.payload.EbookResponse;
import com.mm.libraryrestapi.services.EbookService;
import com.mm.libraryrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ebooks")
public class EbookController {
    private final EbookService ebookService;

    public EbookController(EbookService ebookService) {
        this.ebookService = ebookService;
    }

    @GetMapping("{ebookId}")
    public ResponseEntity<EbookDto> getEbookById(@PathVariable Long ebookId) {
        return ResponseEntity.ok(ebookService.getEbookById(ebookId));
    }

    @PostMapping("")
    public ResponseEntity<EbookDto> createEbook(@Valid @RequestBody EbookDto ebookDto) {
        return ResponseEntity.ok(ebookService.createEbook(ebookDto));
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

    @PutMapping("{ebookId}")
    public ResponseEntity<EbookDto> updateEbookById(@Valid @RequestBody EbookDto ebookDto, @PathVariable Long ebookId){
        return ResponseEntity.ok(ebookService.updateEbookById(ebookDto, ebookId));
    }

    @DeleteMapping("{ebookId}")
    public ResponseEntity<String> deleteEbookById(@PathVariable Long ebookId){
        ebookService.deleteEbookById(ebookId);
        return ResponseEntity.ok("Ebook was successfully deleted");
    }
}
