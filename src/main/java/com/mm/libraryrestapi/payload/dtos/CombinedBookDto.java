package com.mm.libraryrestapi.payload.dtos;

import com.mm.libraryrestapi.entity.Author;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CombinedBookDto {
    private Long id;

    private String title;
    private String tags;
    private String summary;

    private String ISBN;

    private String genre;

    private int publicationYear;

    private Author author;
    private String downloadLink;
    private String readingLink;
    private int availableCopies;
    private int totalCopies;
    private String bookType;
}
