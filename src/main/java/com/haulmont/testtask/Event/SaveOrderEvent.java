package com.haulmont.testtask.Event;

import com.haulmont.testtask.Model.Order;

/**
 * Created by Cok on 15.04.2017.
 */
public class SaveOrderEvent {

    private Order order;

    public SaveOrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
