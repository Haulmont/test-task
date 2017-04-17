package com.haulmont.testtask.Model.dao;

import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;

import java.util.List;

/**
 * Created by Cok on 17.04.2017.
 */
public interface OrderDao extends Dao<Order> {
    List<Order> findByCustomerId(Customer id);
}
