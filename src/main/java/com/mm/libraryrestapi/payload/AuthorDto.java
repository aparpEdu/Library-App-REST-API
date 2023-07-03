package com.mm.libraryrestapi.payload;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AuthorDto {
    private long Id;
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthDate;
    private LocalDate deathDate;
}
