package com.haulmont.testtask.Event;

import com.haulmont.testtask.Model.Customer;

/**
 * Created by Cok on 16.04.2017.
 */
public class SaveNewCustomerEvent {
    private final Customer customer;

    public SaveNewCustomerEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
