package com.education.exception;

public class StorageFileNotFoundException extends StorageException {

    private static final long serialVersionUID = 1L;

    public StorageFileNotFoundException(String message, int statusCode) {
        super(message, statusCode);
    }

}