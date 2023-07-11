//package com.mm.libraryrestapi.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "ebooks")
//public class Ebook {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @Column(nullable = false)
//    private String title;
//    private String tags;
//    private String summary;
//    @Column(nullable = false, unique = true)
//    private String ISBN;
//    @Column(nullable = false)
//    private String genre;
//    @Column(nullable = false)
//    private int publicationYear;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "author_id", nullable = false)
//    private Author author;
//    private String downloadLink;
//    private String readingLink;
//
//}
