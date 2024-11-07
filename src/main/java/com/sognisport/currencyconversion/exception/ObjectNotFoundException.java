package com.sognisport.currencyconversion.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
    public ObjectNotFoundException(String message, Throwable rootCause){
        super(message,rootCause);
    }
}
