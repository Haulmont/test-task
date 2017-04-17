package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.AddCustomerEvent;
import com.haulmont.testtask.Event.ChangeCustomerEvent;
import com.haulmont.testtask.Event.DeleteCustomerEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Customer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Cok on 13.04.2017.
 */
public class CustomerViewVaadin extends VerticalLayout implements CustomerView {

    private UI mainView;
    private MainEventBus mainEventBus;
    private ChangeCustomerLayout changeCustomerLayout;
    private Customer customer;

    public CustomerViewVaadin(UI mainView, MainEventBus mainEventBus) {
        this.mainView = mainView;
        this.mainEventBus = mainEventBus;
        this.mainEventBus.register(this);
    }

    @Override
    public CustomerWindow addCustomer(AddCustomerEvent event) {
        AddCustomerWindowImpl customerWindow = new AddCustomerWindowImpl(mainEventBus);
        mainView.addWindow(customerWindow);
        return customerWindow;
    }

    @Override
    public CustomerWindow changeCustomer(ChangeCustomerEvent event) {
        ChangeCustomerWindowImpl customerWindow = new ChangeCustomerWindowImpl(mainEventBus, customer);
        mainView.addWindow(customerWindow);
        return customerWindow;
    }

    @Override
    public CustomerView deleteCustomer(DeleteCustomerEvent event) {
        mainView.addWindow(new AcceptDeleteWindow(mainEventBus, this.customer));
        return null;
    }

    @Override
    public CustomerView show(List<Customer> customers) {
        this.removeAllComponents();
        Component grid = initCustomerGrid(customers);


        addComponents(grid, changeCustomerLayout);
        setMargin(true);
        this.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
        mainView.setContent(this);
        return this;
    }

    @Override
    public CustomerView showDeleteError() {
        Notification.show("Невозможно удалить клиента, для которого существуют заказы", Notification.Type.ERROR_MESSAGE);
        return this;
    }

    private Component initCustomerGrid(List<Customer> customers) {
        Grid customerGrid = new Grid(new BeanItemContainer<>(Customer.class,
                customers));
        customerGrid.setColumns("id", "firstName", "lastName", "thirdName", "phone");
        customerGrid.setWidth("100%");

        changeCustomerLayout = new ChangeCustomerLayout(mainEventBus);

        customerGrid.addSelectionListener(listener -> {
            Set<Object> itemsIds = listener.getSelected();
            Iterator<Object> iterator = itemsIds.iterator();
            Object itemId = new Object();
            while (iterator.hasNext()) {
                 itemId = iterator.next();
            }

            this.customer = (Customer) itemId;
            changeCustomerLayout.setChangeEnabled(true);
        });
        return customerGrid;
    }
}
