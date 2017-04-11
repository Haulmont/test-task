package com.haulmont.testtask.View;


import com.haulmont.testtask.Model.Customer;

/**
 * Clients view contains information about clients.
 * <p>It should look like a table with client properties</p>
 */
public interface ClientsView {
    ClientsView addCustomer(Customer customer);
    ClientsView changeCustomer(Customer customer);
    ClientsView deleteCustomer(Customer customer);
}
