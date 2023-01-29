package com.example.demo.exceptions;

public class FileReadingException extends RuntimeException {
    public FileReadingException(final String message) {
        super(message);
    }
}
