package com.haulmont.testtask.Model.dao;

import com.haulmont.testtask.Model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * CustomerHibernateDao
 */
public class CustomerHibernateDao implements Dao<Customer> {

    @Override
    public Optional<Customer> findById(Long id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Long create(Customer object) {
        return null;
    }

    @Override
    public boolean update(Customer object) {
        return false;
    }

    @Override
    public boolean delete(Customer object) {
        return false;
    }

    @Override
    public Optional<Exception> getException() {
        return null;
    }
}
