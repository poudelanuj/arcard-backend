package com.dallotech.arcard.exception;

public class ImageFileNotFoundException extends RuntimeException {

    public ImageFileNotFoundException(String message) {
        super(message);
    }

    public ImageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
