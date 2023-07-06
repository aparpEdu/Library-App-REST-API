package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class CombinedBookResponse extends BookResponse {
    private List<CombinedBookDto> content;
}
