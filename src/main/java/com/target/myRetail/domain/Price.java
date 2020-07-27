package com.target.myRetail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
/**
 * Price domain class.
 * @author Preetika
 */
public class Price {
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("currency_code")
    private String currency;

    public Price() {
    }

    public Price(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
