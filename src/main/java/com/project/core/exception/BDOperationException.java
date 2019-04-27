package com.project.core.exception;

public class BDOperationException extends RuntimeException {

    public BDOperationException() {
    }

    public BDOperationException(String message) {
        super(message);
    }
}
