package org.example.db.core;

public class DataAccessException extends RuntimeException {

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable ex) {
        super(message, ex);
    }
}
