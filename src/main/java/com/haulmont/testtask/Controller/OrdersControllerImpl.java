package com.haulmont.testtask.Controller;

import com.haulmont.testtask.Event.*;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.Model.dao.OrderDao;
import com.haulmont.testtask.View.OrdersView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cok on 11.04.2017.
 */
public class OrdersControllerImpl implements OrderController {
    private final OrderDao orderDao;
    private final OrdersView ordersView;
    private final MainEventBus bus;
    private final Dao<Customer> customerDao;

    public OrdersControllerImpl(OrderDao orderDao, Dao<Customer> customerDao, OrdersView ordersView, MainEventBus bus) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
        this.ordersView = ordersView;
        this.bus = bus;
        this.bus.register(this);
    }

    @Override
    public void showOrders(ShowOrderEvent event) {
        List<Order> allOrders = orderDao.findAll();
        ordersView.show(allOrders);
    }

    @Override
    public void showFilteredOrders(FilterOrderEvent event) {
        Customer customer = event.getCustomer();
        String description = event.getDescription();
        OrderStatus status = event.getStatus();

        List<Order> byCustomerId;
        if (customer != null) {
            byCustomerId = orderDao.findByCustomerId(customer);
        } else {
            byCustomerId = orderDao.findAll();
        }

        List<Order> filteredOrders = byCustomerId.stream()
                .filter(order -> order.getDescription().contains(description)
                        && (status == null || order.getStatus().equals(status)))
                .collect(Collectors.toList());

        ordersView.show(filteredOrders);
    }

    @Override
    public void addOrder(AddOrderEvent event) {
        ordersView.addOrder(customerDao.findAll());
    }

    @Override
    public void updateOrder(SaveOrderEvent event) {
        orderDao.update(event.getOrder());
        ordersView.show(orderDao.findAll());
    }

    @Override
    public void saveNewOrder(SaveNewOrderEvent event) {
        orderDao.create(event.getOrder());
        ordersView.show(orderDao.findAll());
    }

    @Override
    public void deleteOrder(AcceptDeleteEvent event) {
        if (event.getObject() instanceof Order) {
            Order order = (Order) event.getObject();
            orderDao.delete(order);
            ordersView.show(orderDao.findAll());
        }
    }

}
