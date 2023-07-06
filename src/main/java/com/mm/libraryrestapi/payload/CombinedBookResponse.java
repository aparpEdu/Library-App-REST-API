package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class CombinedBookResponse extends GeneralResponse {
    private List<CombinedBookDto> content;
}
