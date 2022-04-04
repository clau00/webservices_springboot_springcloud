package com.learning.microservices.currencyexchangeservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
//        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        Optional<CurrencyExchange> currencyExchangeOpt = repository.findByFromAndTo(from, to);

        if(!currencyExchangeOpt.isPresent()) {
            throw  new RuntimeException("Unable to find data for " + from + " to " + to);
        }

        CurrencyExchange currencyExchange = currencyExchangeOpt.get();

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
