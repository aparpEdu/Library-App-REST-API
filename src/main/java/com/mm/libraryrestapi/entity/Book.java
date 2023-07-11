package com.mm.libraryrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    private String tags;
    private String summary;
    @Column(nullable = false, unique = true)
    private String ISBN;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private int publicationYear;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @Column(columnDefinition = "integer default 0")
    private int availableCopies;
    @Column(columnDefinition = "integer default 0")
    private int totalCopies;
    @Column(columnDefinition = "varchar(255) default 'No URL Available'")
    private String downloadLink;
    @Column(columnDefinition = "varchar(255) default 'No URL Available'")
    private String readingLink;
}
