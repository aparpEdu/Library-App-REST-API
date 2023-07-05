//package com.mm.libraryrestapi.utils;
//
//import com.mm.libraryrestapi.entity.Author;
//import com.mm.libraryrestapi.entity.Book;
//import com.mm.libraryrestapi.entity.Ebook;
//import com.mm.libraryrestapi.exception.ResourceNotFoundException;
//import com.mm.libraryrestapi.payload.BookDto;
//import com.mm.libraryrestapi.payload.EbookDto;
//import com.mm.libraryrestapi.payload.PaperBookDto;
//import com.mm.libraryrestapi.repositories.AuthorRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public class BookUtils {
//    public static void updateEbookFields(EbookDto ebookDto, Ebook ebookToUpdate, AuthorRepository authorRepository) {
//        updateBookFields(ebookDto, ebookToUpdate, authorRepository);
//        if (ebookDto.getDownloadLink() != null) {
//            ebookToUpdate.setDownloadLink(ebookDto.getDownloadLink());
//        }
//        if (ebookDto.getReadingLink() != null) {
//            ebookToUpdate.setReadingLink(ebookDto.getReadingLink());
//        }
//    }
//
//    public static void updateBookFields(PaperBookDto paperBookDto, Book bookToUpdate, AuthorRepository authorRepository) {
//        Author author = authorRepository.findById(paperBookDto.getAuthorId())
//                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", paperBookDto.getAuthorId()));
//        bookToUpdate.setAuthor(author);
//        bookToUpdate.setISBN(paperBookDto.getISBN());
//        bookToUpdate.setGenre(paperBookDto.getGenre());
//        bookToUpdate.setSummary(paperBookDto.getSummary());
//        bookToUpdate.setTitle(paperBookDto.getTitle());
//        bookToUpdate.setTags(paperBookDto.getTags());
//        bookToUpdate.setPublicationYear(paperBookDto.getPublicationYear());
//        Book paperBook = (PaperBook) bookToUpdate;
//        paperBook.setAvailableCopies(paperBookDto.getAvailableCopies());
//        paperBook.setTotalCopies(paperBookDto.getTotalCopies());
//    }
//}
