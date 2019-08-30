package com.company.retailservice.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
