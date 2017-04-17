package com.haulmont.testtask.View;

import com.google.common.eventbus.Subscribe;
import com.haulmont.testtask.Event.ChangeOrderEvent;
import com.haulmont.testtask.Event.DeleteOrderEvent;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;

import java.util.List;

/**
 * Orders view contains information about orders.
 * <p>It should look like a table with order properties</p>
 */
public interface OrdersView {

    /**
     * Add order to view table
     *
     * @return self reference
     * @param possibleCustomers customer list from which you can choose applicable one
     */
    OrderWindow addOrder(List<Customer> possibleCustomers);

    /**
     * Change order in the view table
     * @param event event to open change order window
     * @return self reference
     */
    @Subscribe
    OrderWindow changeOrder(ChangeOrderEvent event);

    /**
     * Delete order in the view table
     * @param event delete event
     * @return self reference
     */
    @Subscribe
    OrdersView deleteOrder(DeleteOrderEvent event);

    /**
     * Show view table of orders
     * @return self reference
     */
    OrdersView show(List<Order> orderList);

}
