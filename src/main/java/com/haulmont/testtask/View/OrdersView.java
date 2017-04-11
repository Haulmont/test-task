package com.haulmont.testtask.View;

import com.haulmont.testtask.Model.Order;

/**
 * Orders view contains information about orders.
 * <p>It should look like a table with order properties</p>
 */
public interface OrdersView {

    /**
     * Add order to view table
     * @param order model
     * @return self reference
     */
    OrdersView addOrder(Order order);

    /**
     * Change order in the view table
     * @param order model
     * @return self reference
     */
    OrdersView changeOrder(Order order);

    /**
     * Delete order in the view table
     * @param order model
     * @return self reference
     */
    OrdersView deleteOrder(Order order);

    /**
     * Show view table of orders
     * @return self reference
     */
    OrdersView show();
}
