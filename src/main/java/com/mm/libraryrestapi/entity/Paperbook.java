package com.mm.libraryrestapi.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("paperbook")
public class Paperbook extends Book{
    private int availableCopies;
    private int totalCopies;
}
