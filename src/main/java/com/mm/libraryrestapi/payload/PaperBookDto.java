package com.mm.libraryrestapi.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaperBookDto extends BookDto{
    private int availableCopies;
    private int totalCopies;
}
