package com.haulmont.testtask.Controller;

import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.dao.Dao;

/**
 * Default implementation of Customer controller
 */
public class CustomerControllerImpl implements CustomerController {
    private final Dao<Customer> customerDao;

    public CustomerControllerImpl(Dao<Customer> customerDao) {
        this.customerDao = customerDao;
    }
}
