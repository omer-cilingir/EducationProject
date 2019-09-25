package com.education.exception;

public class StorageException extends EducationException {

    private static final long serialVersionUID = 1L;

    public StorageException(String message, int statusCode) {
        super(message, statusCode);
    }


}
