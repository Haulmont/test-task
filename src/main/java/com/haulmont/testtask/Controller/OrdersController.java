package com.haulmont.testtask.Controller;

import com.haulmont.testtask.Model.Order;

import java.util.List;

/**
 * Represent orders controller which has access to orders model and manipulate it
 */
public interface OrdersController {

    /**
     * Get order list from order model
     * @return order list
     */
    List<Order> getAllOrders();
}
