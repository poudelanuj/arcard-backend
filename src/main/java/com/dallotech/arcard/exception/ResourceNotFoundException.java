package com.dallotech.arcard.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String customMessage){
        super(customMessage);
    }
}
