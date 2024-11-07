package com.sognisport.currencyconversion;

import com.sognisport.currencyconversion.domain.dto.ConversionRate;
import com.sognisport.currencyconversion.domain.dto.ConversionRateDTO;
import com.sognisport.currencyconversion.domain.entity.ExchangeRateResponse;
import com.sognisport.currencyconversion.repository.CurrencyConversionRepository;
import com.sognisport.currencyconversion.service.ExchangRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;

    @InjectMocks
    private ExchangRateService exchangRateService;
    @Test
    void exchangeRate_returnConversionRate(){

        //simulando a resposta da API externa
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setConversion_rate(5.0);
        when(restTemplate.getForObject(anyString(),any())).thenReturn(exchangeRateResponse);

        when(currencyConversionRepository.save(any(ConversionRate.class))).thenAnswer(i-> i.getArgument(0));

        ConversionRate result = exchangRateService.getExchangeRate("USD", "BRL");

        assertNotNull(result);
        assertEquals(5.0, result.getRate());
        assertEquals("USD", result.getFromCurrency());
        assertEquals("BRL", result.getToCurrency());
    }
}
