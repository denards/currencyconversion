package com.sognisport.currencyconversion.exception;

public class ExternalApiException extends RuntimeException {

    public ExternalApiException(String message){
        super(message);
    }

    public ExternalApiException(String message, Throwable rootCause){

    }
}
