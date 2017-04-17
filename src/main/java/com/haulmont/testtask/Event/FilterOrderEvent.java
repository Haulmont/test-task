package com.haulmont.testtask.Event;

import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.OrderStatus;

/**
 * Created by Cok on 17.04.2017.
 */
public class FilterOrderEvent {
    private final Customer customer;
    private final String description;
    private final OrderStatus status;

    public FilterOrderEvent(Customer customer, String description, OrderStatus status) {
        this.customer = customer;
        this.description = description;
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Customer getCustomer() {
        return customer;
    }
}
