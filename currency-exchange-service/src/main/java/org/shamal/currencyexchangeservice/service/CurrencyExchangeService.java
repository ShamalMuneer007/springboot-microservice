package org.shamal.currencyexchangeservice.service;

import org.shamal.currencyexchangeservice.dto.CurrencyExchangeDto;
import org.shamal.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.stereotype.Service;


public interface CurrencyExchangeService{

    void createRecord(CurrencyExchangeDto currencyExchangeDto);

    CurrencyExchange findRecordByToAndFrom(String to, String from);
}
