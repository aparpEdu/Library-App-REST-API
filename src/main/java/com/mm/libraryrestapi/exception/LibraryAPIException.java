package com.mm.libraryrestapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class LibraryAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public LibraryAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}