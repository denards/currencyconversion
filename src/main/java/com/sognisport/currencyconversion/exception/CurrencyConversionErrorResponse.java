package com.sognisport.currencyconversion.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CurrencyConversionErrorResponse {

    private int status;
    private String message;
    private String path;
    private LocalDateTime localDateTime;
}
