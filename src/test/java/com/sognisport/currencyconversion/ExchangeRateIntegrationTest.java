package com.sognisport.currencyconversion;

import com.sognisport.currencyconversion.controller.ExchangeRateController;
import com.sognisport.currencyconversion.domain.entity.ConversionRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class ExchangeRateIntegrationTest {
    @Autowired
    private ExchangeRateController controller;

    @Test
    void shouldReturnExchangeRate() {
        ResponseEntity<ConversionRate> response =
                controller.getExchangeRate("USD", "BRL");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}