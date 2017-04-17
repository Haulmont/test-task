package com.haulmont.testtask.Event;

import com.haulmont.testtask.Model.Order;
import lombok.NonNull;

/**
 * Created by Cok on 15.04.2017.
 */
public class ChangeOrderEvent {
    private Order order;

    public ChangeOrderEvent(@NonNull Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
