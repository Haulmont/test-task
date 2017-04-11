package com.haulmont.testtask.View;

import com.haulmont.testtask.Controller.OrdersController;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;

import java.util.List;

/**
 * Created by Cok on 11.04.2017.
 */
public class OrdersViewVaadin implements OrdersView {

    private Grid orderGrid;

    private final OrdersController ordersController;

    public OrdersViewVaadin(OrdersController ordersController) {
        this.ordersController = ordersController;
    }

    @Override
    public OrdersView addOrder(Order order) {
        return null;
    }

    @Override
    public OrdersView changeOrder(Order order) {
        return null;
    }

    @Override
    public OrdersView deleteOrder(Order order) {
        return null;
    }

    @Override
    public OrdersView show() {
        List<Order> orderList = ordersController.getAllOrders();
        orderGrid = new Grid(new BeanItemContainer<>(Order.class,
                orderList));
        orderGrid.setColumns("Описание", "Клиент", "Дата создания", "Дата окончания работ", "Стоимость", "Статус");
        orderGrid.setWidth("80%");
        orderGrid.addRow(Order.of(1L, new Customer()));
        return this;
    }
}
