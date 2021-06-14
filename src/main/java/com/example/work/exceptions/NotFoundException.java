package com.example.work.exceptions;

public class NotFoundException extends RuntimeException {

    private Long id;
    private static final String message = "Product not found with id: ";

    public NotFoundException(Long id) {
        super(message + id);
    }


}
