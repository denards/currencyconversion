package com.sognisport.currencyconversion.service;

import com.sognisport.currencyconversion.domain.dto.ConversionRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ExchangRateService {


    //analisar uma troca por WebClient

    private final RestTemplate restTemplate;

    @Value("${com.sognisport.exchange.apikey}")
    private String apiKey;

    public ExchangRateService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ConversionRate getExchangeRate(String fromCurrency, String toCurrency){

        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",apiKey, fromCurrency, toCurrency);
        ExchangeRateResponse exchangeRateResponse = restTemplate.getForObject(url, ExchangeRateResponse.class);
        ConversionRate conversionRate = new ConversionRate();
        conversionRate.setFromCurrency(fromCurrency);
        conversionRate.setToCurrency(toCurrency);
        conversionRate.setRate(exchangeRateResponse.getConversion_rate());
        conversionRate.setTimestamp(LocalDateTime.now());

        return conversionRate;
    }

    private static class ExchangeRateResponse {
        private Double conversion_rate;


        public Double getConversion_rate() {
            return conversion_rate;
        }

        public void setConversion_rate(Double conversion_rate) {
            this.conversion_rate = conversion_rate;
        }
    }
}
