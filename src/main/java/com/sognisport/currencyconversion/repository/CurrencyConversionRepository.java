package com.sognisport.currencyconversion.repository;

import com.sognisport.currencyconversion.domain.entity.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConversionRepository extends JpaRepository<ConversionRate, Long> {
}
