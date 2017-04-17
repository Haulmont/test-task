package com.haulmont.testtask.Event;

import com.haulmont.testtask.Model.Customer;

/**
 * Created by Cok on 16.04.2017.
 */
public class SaveCustomerEvent {
    private final Customer customer;

    public SaveCustomerEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
