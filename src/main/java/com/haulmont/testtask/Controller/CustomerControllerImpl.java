package com.haulmont.testtask.Controller;

import com.haulmont.testtask.Event.AcceptDeleteEvent;
import com.haulmont.testtask.Event.SaveCustomerEvent;
import com.haulmont.testtask.Event.SaveNewCustomerEvent;
import com.haulmont.testtask.Event.ShowCustomerEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.View.CustomerView;

import java.util.List;

/**
 * Default implementation of Customer controller
 */
public class CustomerControllerImpl implements CustomerController {
    private final Dao<Customer> customerDao;
    private final CustomerView customerView;

    public CustomerControllerImpl(Dao<Customer> customerDao, CustomerView customerView, MainEventBus mainEventBus) {
        this.customerDao = customerDao;
        this.customerView = customerView;
        mainEventBus.register(this);
    }


    @Override
    public void showCustomers(ShowCustomerEvent event) {
        List<Customer> allCustomers = customerDao.findAll();
        customerView.show(allCustomers);
    }

    @Override
    public void saveNewCustomer(SaveNewCustomerEvent event) {
        customerDao.create(event.getCustomer());
        customerView.show(customerDao.findAll());
    }

    @Override
    public void updateCustomer(SaveCustomerEvent event) {
        customerDao.update(event.getCustomer());
        customerView.show(customerDao.findAll());
    }

    @Override
    public void deleteCustomer(AcceptDeleteEvent event) {
        if (event.getObject() instanceof Customer) {
            Customer customer = (Customer) event.getObject();
            boolean isSuccess = customerDao.delete(customer);
            customerView.show(customerDao.findAll());
            if (!isSuccess) customerView.showDeleteError();
        }
    }
}
