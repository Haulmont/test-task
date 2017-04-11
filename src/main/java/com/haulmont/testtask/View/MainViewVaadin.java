package com.haulmont.testtask.View;

import com.haulmont.testtask.Controller.CustomerController;
import com.haulmont.testtask.Controller.OrdersController;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * Implementation of MainView with Vaadin framework
 */
public class MainViewVaadin implements MainView {

    private final CustomerController customerController;
    private final OrdersController ordersController;

    private VerticalLayout verticalLayout = new VerticalLayout();

    public MainViewVaadin(CustomerController customerController, OrdersController ordersController) {
        this.customerController = customerController;
        this.ordersController = ordersController;
    }

    @Override
    public OrdersView showOrders() {
        OrdersView ordersView = new OrdersViewVaadin(ordersController);
        ordersView.show();
        return ordersView;
    }

    @Override
    public ClientsView showClients() {
        return null;
    }

    @Override
    public void show() {
        verticalLayout.setMargin(true);
        verticalLayout.setSizeFull();
        Button showOrdersBtn = new Button("Посмотреть заказы");
        showOrdersBtn.addClickListener(event -> {
            showOrders();
        });



    }
}
