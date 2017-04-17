package com.haulmont.testtask.Controller;

import com.google.common.eventbus.Subscribe;
import com.haulmont.testtask.Event.*;

/**
 * Represent customer controller which has access to customer model and manipulate it
 */
public interface CustomerController {

    /**
     * Show customer table when controller get show customer event
     *
     * @param event show customer event that was transferred by event bus
     */
    @Subscribe
    void showCustomers(ShowCustomerEvent event);

    @Subscribe
    void saveNewCustomer(SaveNewCustomerEvent event);

    @Subscribe
    void updateCustomer(SaveCustomerEvent event);

    @Subscribe
    void deleteCustomer(AcceptDeleteEvent event);
}
