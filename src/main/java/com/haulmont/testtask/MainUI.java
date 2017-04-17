package com.haulmont.testtask;

import com.haulmont.testtask.Controller.*;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.Model.dao.OrderDao;
import com.haulmont.testtask.Model.dao.OrderHibernateDao;
import com.haulmont.testtask.View.*;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Dao<Customer> customerDao = new CustomerHibernateDao();
        OrderDao orderDao = new OrderHibernateDao();

        MainEventBus mainEventBus = MainEventBusImpl.getInstance();

        OrdersView ordersView = new OrdersViewVaadin(this, mainEventBus);
        CustomerView customerView = new CustomerViewVaadin(this, mainEventBus);
        MainViewVaadin mainViewVaadin = new MainViewVaadin(this, mainEventBus);

        CustomerController customerController = new CustomerControllerImpl(customerDao, customerView, mainEventBus);
        OrderController ordersController = new OrdersControllerImpl(orderDao, customerDao, ordersView, mainEventBus);
        MainController mainController = new MainController(mainViewVaadin, mainEventBus);

        setContent(mainViewVaadin);

        //it exists here for test needs, it can be safely deleted
        TestUtil.getInstance().loadTestData();
    }


}