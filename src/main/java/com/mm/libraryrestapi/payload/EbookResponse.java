package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class EbookResponse extends BookResponse{
    private List<EbookDto> ebookDtos;
}
