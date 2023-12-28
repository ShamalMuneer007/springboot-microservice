package org.shamal.currencyconversionservice.proxies;

import org.shamal.currencyconversionservice.dto.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange",path = "/api/v1/currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable(name = "from") String from,
                                               @PathVariable(name = "to") String to);

}
