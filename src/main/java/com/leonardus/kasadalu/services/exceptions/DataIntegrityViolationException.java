package com.leonardus.kasadalu.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
