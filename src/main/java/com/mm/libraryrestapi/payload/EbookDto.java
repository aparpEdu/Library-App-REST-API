package com.mm.libraryrestapi.payload;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;
@EqualsAndHashCode(callSuper = true)
@Data
public class EbookDto extends BookDto {
    @URL
    private String downloadLink;
    @URL
    private String readingLink;
}
