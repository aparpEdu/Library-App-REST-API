package com.mm.libraryrestapi.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class EbookDto  {
    @NotEmpty(message = "Book title should not be null or empty")
    @Size(min = 2, message = "Book title should have at least 2 characters")
    private String title;

    @NotEmpty(message = "Book tags should not be null or empty")
    @Size(min = 4, message = "Book tags should have at least 4 characters")
    private String tags;

    @NotEmpty(message = "Book summary should not be null or empty")
    @Size(min = 10, message = "Book summary should have at least 10 characters")
    private String summary;

    @NotEmpty(message = "Book ISBN should not be null or empty")
    @Size(min = 13, max = 13, message = "Book ISBN should be 13 digits long")
    private String ISBN;

    @NotEmpty(message = "Book genre should not be null or empty")
    @Size(min = 4, message = "Book genre should have at least 4 characters")
    private String genre;
    @NotNull(message = "Author cannot be null")
    private Long authorId;
    @NotNull(message = "Book publication year cannot be null")
    private Integer publicationYear;

    @URL
    private String downloadLink;
    @URL
    private String readingLink;
}
