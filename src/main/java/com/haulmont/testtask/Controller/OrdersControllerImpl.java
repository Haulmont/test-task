package com.haulmont.testtask.Controller;

import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.dao.Dao;

import java.util.List;

/**
 * Created by Cok on 11.04.2017.
 */
public class OrdersControllerImpl implements OrdersController {
    private final Dao<Order> orderDao;

    public OrdersControllerImpl(Dao<Order> orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
}
