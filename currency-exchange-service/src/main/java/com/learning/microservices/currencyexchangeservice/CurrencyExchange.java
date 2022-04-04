package com.learning.microservices.currencyexchangeservice;

import java.math.BigDecimal;

public class CurrencyExchange {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultipe;
    private String environment;

    public CurrencyExchange() {}

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultipe) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultipe = conversionMultipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultipe() {
        return conversionMultipe;
    }

    public void setConversionMultipe(BigDecimal conversionMultipe) {
        this.conversionMultipe = conversionMultipe;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
