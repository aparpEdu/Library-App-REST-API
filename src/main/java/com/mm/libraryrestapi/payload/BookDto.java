package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
