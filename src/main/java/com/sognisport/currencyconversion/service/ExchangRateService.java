package com.sognisport.currencyconversion.service;

import com.sognisport.currencyconversion.domain.entity.ConversionRate;
import com.sognisport.currencyconversion.domain.dto.ConversionRateDTO;
import com.sognisport.currencyconversion.domain.entity.ExchangeRateResponse;
import com.sognisport.currencyconversion.exception.ObjectNotFoundException;
import com.sognisport.currencyconversion.repository.CurrencyConversionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
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

        log.info("Câmbio de moedas entre {} e {}", fromCurrency, toCurrency);
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, fromCurrency, toCurrency);

        log.debug("Chamando api externa de câmbio ExchangeAPI {}",url);
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


    public List<ConversionRate> listAllExchangeRates() {
        log.info("Listando câmbios já realizados no database");
        return currencyConversionRepository.findAll();
    }

    public ConversionRate updateConversionRate(ConversionRateDTO conversionRateDTO) {
        log.info("Atualizando câmbio para o id {}",conversionRateDTO.getId());
        Optional<ConversionRate> op = currencyConversionRepository.findById(conversionRateDTO.getId());
        op.ifPresentOrElse(
                (conversionRate -> conversionRate.setRate(conversionRateDTO.getRate())),
                () -> new ObjectNotFoundException("object " + conversionRateDTO.getId() + " not found")
        );
        currencyConversionRepository.save(op.get());
        return op.get();
    }
}
