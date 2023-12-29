package org.shamal.currencyconversionservice.controllers;
import org.shamal.currencyconversionservice.dto.CurrencyConversion;
import org.shamal.currencyconversionservice.proxies.CurrencyExchangeProxy;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/currency-conversion")
public class CurrencyConversionController {

    private final  Environment environment;
    private final CurrencyExchangeProxy proxy;

    private final RestTemplate restTemplate;

    public CurrencyConversionController(Environment environment, CurrencyExchangeProxy proxy, RestTemplate restTemplate) {
        this.environment = environment;
        this.proxy = proxy;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable(name = "from") String from,
                                                          @PathVariable(name = "to") String to,
                                                          @PathVariable(name = "quantity") Double quantity){
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from,to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity* currencyConversion.getConversionMultiple());
        return currencyConversion;
    }
}
