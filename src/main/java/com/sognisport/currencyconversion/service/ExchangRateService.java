package com.sognisport.currencyconversion.service;

import com.sognisport.currencyconversion.domain.dto.ConversionRate;
import com.sognisport.currencyconversion.domain.dto.ConversionRateDTO;
import com.sognisport.currencyconversion.domain.entity.ExchangeRateResponse;
import com.sognisport.currencyconversion.exception.ObjectNotFoundException;
import com.sognisport.currencyconversion.repository.CurrencyConversionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExchangRateService {


    //analisar uma troca por WebClient

    private final RestTemplate restTemplate;
    private final CurrencyConversionRepository currencyConversionRepository;

    @Value("${com.sognisport.exchange.apikey}")
    private String apiKey;

    public ExchangRateService(RestTemplate restTemplate, CurrencyConversionRepository currencyConversionRepository) {
        this.restTemplate = restTemplate;
        this.currencyConversionRepository = currencyConversionRepository;
    }

    public ConversionRate getExchangeRate(String fromCurrency, String toCurrency) {

        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, fromCurrency, toCurrency);
        ExchangeRateResponse exchangeRateResponse = restTemplate.getForObject(url, ExchangeRateResponse.class);
        ConversionRate conversionRate = null;
        if (!Objects.isNull(exchangeRateResponse)) {
            conversionRate = new ConversionRate();
            conversionRate.setFromCurrency(fromCurrency);
            conversionRate.setToCurrency(toCurrency);
            conversionRate.setRate(exchangeRateResponse.getConversion_rate());
            conversionRate.setTimestamp(LocalDateTime.now());
        }

        return currencyConversionRepository.save(conversionRate);
    }


    public List<ConversionRate> listAllExcnahgeRates() {
        return currencyConversionRepository.findAll();
    }

    public ConversionRate updateConversionRate(ConversionRateDTO conversionRateDTO) {
        Optional<ConversionRate> op = currencyConversionRepository.findById(conversionRateDTO.getId());
        op.ifPresentOrElse(
                (conversionRate -> conversionRate.setRate(conversionRateDTO.getRate())),
                () -> new ObjectNotFoundException("object " + conversionRateDTO.getId() + " not found")
        );

        return op.get();
    }
}
