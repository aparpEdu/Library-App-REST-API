package com.mm.libraryrestapi.payload.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private long Id;

    @NotEmpty(message = "First name should not be null or empty")
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;

    @NotEmpty(message = "Last name should not be null or empty")
    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastName;

    @NotEmpty(message = "Country should not be null or empty")
    @Size(min = 2, message = "Country should have at least 4 characters")
    private String country;

    @NotNull(message = "Birth date should not be null ")
    private LocalDate birthDate;
    private LocalDate deathDate;
}
