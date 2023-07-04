package com.mm.libraryrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private long Id;
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthDate;
    private LocalDate deathDate;
}
