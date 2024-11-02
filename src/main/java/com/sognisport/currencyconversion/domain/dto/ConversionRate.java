package com.sognisport.currencyconversion.domain.dto;

import lombok.Builder;
import lombok.Data;


@Builder
public class ConversionRate {
    private String fromCurrency;
    private String toCurrency;
    private Double rate;
}
