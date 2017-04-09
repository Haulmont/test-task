package com.haulmont.testtask.Model;

import lombok.Data;

import java.util.Currency;

/**
 * Represent cost of order
 * <p>Contains currency and value of money</p>
 */
@Data
public class Money {
    private final Currency currency;
    private final Long value;

    public Money(Currency currency, Long value) {
        this.currency = currency;
        this.value = value;
    }
}
