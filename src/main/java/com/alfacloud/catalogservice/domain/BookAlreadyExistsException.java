package com.alfacloud.catalogservice.domain;

public class BookAlreadyExistsException extends Exception {
    public BookAlreadyExistsException(String isbn) {
        super(String.format("Book with ISBN %s already exists", isbn));
    }
}
