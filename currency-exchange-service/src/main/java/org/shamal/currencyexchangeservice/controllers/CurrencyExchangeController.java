package org.shamal.currencyexchangeservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.shamal.currencyexchangeservice.dto.CurrencyExchangeDto;
import org.shamal.currencyexchangeservice.entity.CurrencyExchange;
import org.shamal.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.shamal.currencyexchangeservice.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/currency-exchange")
@Slf4j
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository, CurrencyExchangeService currencyExchangeService) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<Object> exchangeCurrency(@PathVariable(name = "from") String from,
                                                @PathVariable(name = "to") String to){
        String environmentProperty = environment.getProperty("local.server.port");
        try {
            CurrencyExchange currencyExchangeInfo = currencyExchangeService.findRecordByToAndFrom(to, from);
            return new ResponseEntity<>( new CurrencyExchangeDto(currencyExchangeInfo,environmentProperty),
                    HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("No such data",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add-currency-exchange-info")
    public ResponseEntity<String> addCurrencyData(@RequestBody CurrencyExchangeDto currencyExchangeDto){
        try {
            currencyExchangeService.createRecord(currencyExchangeDto);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fail-test")
    @Retry(name = "default",fallbackMethod = "failTestFallback")
    @CircuitBreaker(name = "default",fallbackMethod = "failTestFallback")
    @RateLimiter(name = "default",fallbackMethod = "fallbackMethod")
    public String failTest(){
        log.info("fail-test api called");
        ResponseEntity<String> forEntity =
                new RestTemplate()
                        .getForEntity("http://localhost:8082/hellow-world",String.class);
        return forEntity.getBody();
    }
    public String failTestFallback(Exception e){
        return "I'm just fell off. I'm good";
    }
}
