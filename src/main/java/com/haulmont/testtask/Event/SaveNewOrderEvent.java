package com.haulmont.testtask.Event;

import com.haulmont.testtask.Model.Order;

/**
 * Created by Cok on 16.04.2017.
 */
public class SaveNewOrderEvent {
    private final Order order;

    public SaveNewOrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
