package com.sognisport.currencyconversion.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Data
public class ConversionRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String fromCurrency;
    private String toCurrency;
    private Double rate;
    private LocalDateTime timestamp;

    ;
}
