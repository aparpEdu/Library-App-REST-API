//package com.mm.libraryrestapi.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Entity(name="books")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="book_type",  discriminatorType = DiscriminatorType.STRING)
//public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @Column(nullable = false)
//    private String title;
//    private String tags;
//    private String summary;
//    @Column(nullable = false)
//    private String ISBN;
//    @Column(nullable = false)
//    private String genre;
//    @Column(nullable = false)
//    private int publicationYear;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "author_id", nullable = false)
//    private Author author;
//
//}
