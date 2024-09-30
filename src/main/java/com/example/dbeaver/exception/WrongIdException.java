package com.example.dbeaver.exception;

public class WrongIdException extends RuntimeException {
    public WrongIdException() {
        super();
    }

    public WrongIdException(String message) {
        super(message);
    }

    public WrongIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongIdException(Throwable cause) {
        super(cause);
    }
}
