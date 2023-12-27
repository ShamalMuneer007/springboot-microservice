package org.shamal.currencyexchangeservice.service;

import org.shamal.currencyexchangeservice.dto.CurrencyExchangeDto;
import org.shamal.currencyexchangeservice.entity.CurrencyExchange;
import org.shamal.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    private  final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeServiceImpl(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public void createRecord(CurrencyExchangeDto currencyExchangeDto) {
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setTargetCurrency(currencyExchangeDto.getTo());
        currencyExchange.setSourceCurrency(currencyExchangeDto.getFrom());
        currencyExchange.setConversionMultiple(currencyExchangeDto.getConversionMultiple());
        currencyExchangeRepository.save(currencyExchange);
    }

    @Override
    public CurrencyExchange findRecordByToAndFrom(String to, String from) {
       return currencyExchangeRepository.findByTargetCurrencyAndSourceCurrency(to,from);
    }
}
