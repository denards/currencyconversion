package com.sognisport.currencyconversion;

import com.sognisport.currencyconversion.controller.ExchangeRateController;
import com.sognisport.currencyconversion.domain.entity.ConversionRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.sognisport.currencyconversion.service.ExchangRateService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ExchangeRateController.class)
public class ExchageRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ExchangRateService exchangeRateService;
    @Test
    public void getExchangeRate_returnConversionRate() throws Exception {
        ConversionRate rate = new ConversionRate();
        rate.setFromCurrency("USD");
        rate.setToCurrency("BRL");
        rate.setRate(5.0);
        rate.setTimestamp(LocalDateTime.now());

        when(exchangeRateService.getExchangeRate(anyString(), anyString())).thenReturn(rate);

        mockMvc.perform(get("/api/exchange/rate/USD/BRL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fromCurrency").value("USD"))
                .andExpect(jsonPath("$.toCurrency").value("BRL"))
                .andExpect(jsonPath("$.rate").value(5.0));

    }
}
