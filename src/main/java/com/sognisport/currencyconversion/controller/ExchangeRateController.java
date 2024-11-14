package com.sognisport.currencyconversion.controller;

import com.sognisport.currencyconversion.domain.entity.ConversionRate;
import com.sognisport.currencyconversion.domain.dto.ConversionRateDTO;
import com.sognisport.currencyconversion.service.ExchangRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRateController {

    @Autowired
    ExchangRateService exchangRateService;

    @GetMapping(path = "/rate/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversionRate> getExchangeRate(@PathVariable String from, @PathVariable String to) {
        System.out.println(new StringBuilder("variaveis: ").append(from).append(" ").append(to));

        return ResponseEntity.ok(exchangRateService.getExchangeRate(from, to));
    }

    @GetMapping("/listexchangerates")
    public List<ConversionRate> listExchangeRates() {
        return exchangRateService.listAllExchangeRates();
    }

    @PutMapping("/update")
    public ResponseEntity<ConversionRate> updateConversionRate(@RequestBody ConversionRateDTO conversionRateDTO) {
        return ResponseEntity.ok(exchangRateService.updateConversionRate(conversionRateDTO));
    }
}

