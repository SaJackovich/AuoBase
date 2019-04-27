package com.project.core.exception;

public class NullFormDtoException extends RuntimeException {

    public NullFormDtoException() {
    }

    public NullFormDtoException(String message) {
        super(message);
    }
}
