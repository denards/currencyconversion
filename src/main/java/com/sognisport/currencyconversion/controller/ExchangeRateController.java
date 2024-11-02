package com.sognisport.currencyconversion.controller;

import com.sognisport.currencyconversion.domain.dto.ConversionRate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRateController {

    @GetMapping(path = "/rate/{from}/{to}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversionRate> getExchangeRate(@PathVariable String from, @PathVariable String to) {
        System.out.println(new StringBuilder("variaveis: ").append(from).append(" ").append(to));
        return ResponseEntity.ok(ConversionRate.builder().fromCurrency(from).toCurrency(to).build());
    }
}

