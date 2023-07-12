package com.mm.libraryrestapi.utils;

public class ErrorMessages {
    public static final String PENDING_RETURN = "You have at least one book with a pending return";
    public static final String INVALID_USER = "The borrow history in not from the current user";
    public static final String NO_BOOKS_AVAILABLE = "There are no more paper books available";
    public static final String BOOK_ALREADY_RETURNED = "The book was already returned";
    public static final String INVALID_POSTPONEMENT_DAYS = "Postponement days need to be a value greater than 0";
    public static final String POSTPONE_DATE_LIMIT = "You can't postpone the return date to more than 14 dates from the borrow date";
    public static final String AVAILABLE_BOOKS_BIGGER_THAN_TOTAL = "Total Number of Books should be equal or greater than number of Available Books";
    public static final String BOOK_URL_NOT_FOUND ="Book URL NOT FOUND";
    public static final String BOOK_USER_NO_MATCH = "User and book don't match";
    public static final String EMAIL_SEND_FAILURE = "Failed to send email";
    public static final String EXPIRED_TOKEN = "Token expired";
    public static final String EMAIL_ALREADY_CONFIRMED = "Email already confirmed";
    public static final String TOKEN_NOT_FOUND = "Token not found";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists!";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";
    public static final String NON_EXISTENT_USER = "User Does not exist";
    public static final String NOT_CONFIRMED_EMAIL ="Email has not been confirmed!";
    public static final String WRONG_PASSWORD = "Invalid password. Please try again.";
    public static final String NEW_PASSWORD_NO_MATCH = "Please ensure the new password is entered correctly twice.";
}