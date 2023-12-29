package org.shamal.currencyexchangeservice.dto;

import lombok.*;
import org.shamal.currencyexchangeservice.entity.CurrencyExchange;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CurrencyExchangeDto {
    private long id;
    private String from;
    private String to;
    private Double conversionMultiple;
    private String environment;

    public CurrencyExchangeDto(CurrencyExchange currencyExchangeInfo, String environmentProperty) {
        this.id =currencyExchangeInfo.getId();
        this.from = currencyExchangeInfo.getCurrencyFrom();
        this.to = currencyExchangeInfo.getCurrencyTo();
        this.conversionMultiple = currencyExchangeInfo.getConversionMultiple();
        this.environment = environmentProperty;
    }
}
