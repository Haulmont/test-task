package com.haulmont.testtask.Controller;

import com.google.common.eventbus.Subscribe;
import com.haulmont.testtask.Event.*;

/**
 * Represent orders controller which has access to orders model and manipulate it
 */
public interface OrderController {

    /**
     * Show order table when controller get show order event
     *
     * @param event show order event that was transferred by event bus
     */
    @Subscribe
    void showOrders(ShowOrderEvent event);

    @Subscribe
    void showFilteredOrders(FilterOrderEvent event);

    @Subscribe
    void addOrder(AddOrderEvent event);

    @Subscribe
    void updateOrder(SaveOrderEvent event);

    @Subscribe
    void saveNewOrder(SaveNewOrderEvent event);

    @Subscribe
    void deleteOrder(AcceptDeleteEvent event);
}
