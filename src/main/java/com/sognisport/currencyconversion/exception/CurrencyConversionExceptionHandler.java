package com.sognisport.currencyconversion.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class CurrencyConversionExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<CurrencyConversionErrorResponse> objectNotFound(ObjectNotFoundException e, WebRequest request) {
        log.error("Objeto Não encontrado: {}", e.getMessage());
        CurrencyConversionErrorResponse currencyConversionErrorResponse = new CurrencyConversionErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "ERRO: " + e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(currencyConversionErrorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<CurrencyConversionErrorResponse> externalApiDown(ExternalApiException e, WebRequest request) {
        log.error("Problemas na API de Câmbio: {}", e.getMessage());
        CurrencyConversionErrorResponse currencyConversionErrorResponse = new CurrencyConversionErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "ERRO: " + e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(currencyConversionErrorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CurrencyConversionErrorResponse> restClientError(HttpClientErrorException e, WebRequest request) {
        log.error("Problemas na API REST: {}", e.getMessage());
        CurrencyConversionErrorResponse currencyConversionErrorResponse = new CurrencyConversionErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "ERRO: " + e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(currencyConversionErrorResponse, HttpStatus.NOT_FOUND);

    }
}
