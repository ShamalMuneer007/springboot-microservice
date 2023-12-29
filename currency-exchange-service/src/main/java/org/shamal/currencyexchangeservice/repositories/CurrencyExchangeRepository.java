package org.shamal.currencyexchangeservice.repositories;

import org.shamal.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    CurrencyExchange findByCurrencyToAndCurrencyFrom(String to, String from);
}
