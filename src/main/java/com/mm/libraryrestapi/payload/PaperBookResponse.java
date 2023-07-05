package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PaperBookResponse extends BookResponse{
    private List<BookDto> content;
}
