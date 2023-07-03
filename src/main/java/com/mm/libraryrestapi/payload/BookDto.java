package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Author;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDto {
    private long id;
    private String title;
    private String tags;
    private String summary;
    private String ISBN;
    private String genre;
    private int publicationYear;
    private Author author;
}
