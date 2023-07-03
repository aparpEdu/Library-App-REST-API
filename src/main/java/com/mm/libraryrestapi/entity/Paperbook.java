package com.mm.libraryrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paperbook extends Book{
    private int availableCopies;
    private int totalCopies;
}
