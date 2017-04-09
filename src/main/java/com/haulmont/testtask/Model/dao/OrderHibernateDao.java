package com.haulmont.testtask.Model.dao;

import com.haulmont.testtask.Model.Order;

import java.util.List;
import java.util.Optional;

/**
 * Created by Cok on 09.04.2017.
 */
public class OrderHibernateDao implements Dao<Order> {
    @Override
    public Optional<Order> findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Long create(Order object) {
        return null;
    }

    @Override
    public boolean update(Order object) {
        return false;
    }

    @Override
    public boolean delete(Order object) {
        return false;
    }

    @Override
    public Optional<Exception> getException() {
        return null;
    }
}
