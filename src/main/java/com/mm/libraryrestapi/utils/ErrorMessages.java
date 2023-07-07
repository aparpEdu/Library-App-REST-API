package com.mm.libraryrestapi.utils;

public class ErrorMessages {
    public static final String PENDING_RETURN = "You have at least one book with a pending return";
    public static final String INVALID_USER = "The borrow history in not from the current user";
    public static final String NO_BOOKS_AVAILABLE = "There are no more paper books available";
    public static final String BOOK_ALREADY_RETURNED = "The book was already returned";
    public static final String INVALID_POSTPONEMENT_DAYS = "Postponement days need to be a value greater than 0";
    public static final String POSTPONE_DATE_LIMIT = "You can't postpone the return date to more than 14 dates from the borrow date";
}
