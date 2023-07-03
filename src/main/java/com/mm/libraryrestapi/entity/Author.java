package com.mm.libraryrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthDate;
    private LocalDate deathDate;
}
