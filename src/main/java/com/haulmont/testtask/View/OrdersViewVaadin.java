package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.ChangeOrderEvent;
import com.haulmont.testtask.Event.DeleteOrderEvent;
import com.haulmont.testtask.Event.FilterOrderEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by Cok on 11.04.2017.
 */
public class OrdersViewVaadin extends VerticalLayout implements OrdersView {

    private final UI viewVaadin;
    private final MainEventBus mainEventBus;
    private ChangeOrdersLayout changeOrdersLayout;

    private Grid orderGrid;
    private List<Order> orders;

    public OrdersViewVaadin(UI viewVaadin, MainEventBus mainEventBus) {

        this.viewVaadin = viewVaadin;
        this.mainEventBus = mainEventBus;
        mainEventBus.register(this);
    }

    @Override
    public OrderWindow addOrder(List<Customer> possibleCustomers) {
        AddOrderWindowImpl orderWindow = new AddOrderWindowImpl(possibleCustomers, mainEventBus);
        viewVaadin.addWindow(orderWindow);
        return orderWindow;
    }

    @Override
    public OrderWindow changeOrder(ChangeOrderEvent event) {
        ChangeOrderWindowImpl window = new ChangeOrderWindowImpl(mainEventBus, event.getOrder());
        viewVaadin.addWindow(window);
        return window;
    }

    @Override
    public OrdersView deleteOrder(DeleteOrderEvent event) {
        viewVaadin.addWindow(new AcceptDeleteWindow(mainEventBus, event.getOrder()));
        return this;
    }

    private void updateGrid(Order order) {
        Container.Indexed containerDataSource = orderGrid.getContainerDataSource();
        containerDataSource.addItem(order);
    }


    @Override
    public OrdersView show(List<Order> orders) {
        this.removeAllComponents();
        this.orders = orders;
        Component filter = initFilter();
        Component grid = initOrderGrid(orders);
        addComponent(filter);
        addComponent(grid);
        changeOrdersLayout = new ChangeOrdersLayout(mainEventBus);
        addComponent(changeOrdersLayout);
        setMargin(true);
        this.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);

        viewVaadin.setContent(this);
        return this;
    }

    private Component initFilter() {
        HorizontalLayout filters = new HorizontalLayout();
        filters.setDefaultComponentAlignment(Alignment.TOP_LEFT);
        ComboBox customer = new ComboBox("Клиент");
        customer.setFilteringMode(FilteringMode.CONTAINS);
        List<Customer> customers = new ArrayList<>();
        orders.forEach(order -> customers.add(order.getCustomer()));
        customer.addItems(customers);
        TextField description = new TextField("Описание");
        ComboBox status = new ComboBox("Статус");
        status.addItems((Object[]) OrderStatus.values());
        Button accept = new Button("Применить", listener -> {
            mainEventBus.post(new FilterOrderEvent((Customer)customer.getValue(), description.getValue(), (OrderStatus) status.getValue()));
        });


        filters.setSpacing(true);
        filters.setMargin(false);
        filters.addComponents(customer, description, status, accept);
        filters.setComponentAlignment(customer, Alignment.TOP_LEFT);
        filters.setComponentAlignment(description, Alignment.TOP_LEFT);
        filters.setComponentAlignment(status, Alignment.TOP_LEFT);
        filters.setComponentAlignment(accept, Alignment.TOP_CENTER);

        return filters;
    }

    private Component initOrderGrid(List<Order> orders) {
        BeanItemContainer<Order> orderBeanItemContainer = new BeanItemContainer<>(Order.class,
                orders);

        orderGrid = new Grid(orderBeanItemContainer);
        orderGrid.setColumns("id", "description", "cost", "customer", "createDate", "closeDate", "status");
        orderGrid.setWidth("100%");

        Consumer<Order> setToLayout = order -> changeOrdersLayout.setOrder(order);
        orderGrid.addSelectionListener(listener -> {
            Set<Object> orderSet = listener.getSelected();
            for (Object order : orderSet) {
                setToLayout.accept((Order) order);
            }
            changeOrdersLayout.setChangeEnabled(true);
        });
        return orderGrid;
    }

}
