package com.haulmont.testtask;

import com.haulmont.testtask.Controller.CustomerController;
import com.haulmont.testtask.Controller.CustomerControllerImpl;
import com.haulmont.testtask.Controller.OrdersController;
import com.haulmont.testtask.Controller.OrdersControllerImpl;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.Model.dao.OrderHibernateDao;
import com.haulmont.testtask.View.MainView;
import com.haulmont.testtask.View.MainViewVaadin;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Dao<Customer> customerDao = new CustomerHibernateDao();
        Dao<Order> orderDao = new OrderHibernateDao();

        CustomerController customerController = new CustomerControllerImpl(customerDao);
        OrdersController ordersController = new OrdersControllerImpl(orderDao);

        MainView view = new MainViewVaadin(customerController, ordersController);
        view.show();
    }

}