package com.haulmont.testtask.View;


import com.google.common.eventbus.Subscribe;
import com.haulmont.testtask.Event.AddCustomerEvent;
import com.haulmont.testtask.Event.ChangeCustomerEvent;
import com.haulmont.testtask.Event.DeleteCustomerEvent;
import com.haulmont.testtask.Model.Customer;

import java.util.List;

/**
 * Clients view contains information about clients.
 * <p>It should look like a table with client properties</p>
 */
public interface CustomerView {
    @Subscribe
    CustomerWindow addCustomer(AddCustomerEvent event);

    @Subscribe
    CustomerWindow changeCustomer(ChangeCustomerEvent event);

    @Subscribe
    CustomerView deleteCustomer(DeleteCustomerEvent event);

    CustomerView show(List<Customer> customers);

    CustomerView showDeleteError();
}
