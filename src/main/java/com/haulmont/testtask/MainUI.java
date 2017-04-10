package com.haulmont.testtask;

import com.haulmont.testtask.Model.OrderStatus;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        initOrdersTable();

//        setContent(layout);
    }

    private void initOrdersTable() {
        VerticalLayout components = new VerticalLayout();


        Grid orders = new Grid("Заказы");

        orders.setWidth("80%");
        orders.setHeight("40%");
//        orders.setColumns("Описание", "Клиент", "Дата создания", "Дата окончания работ", "Стоимость", "Статус");
        orders.addColumn("Описание", String.class);
        orders.addColumn("Клиент", String.class);
        orders.addColumn("Статус", String.class);


        orders.addRow("Дескрипшен", OrderStatus.ACCEPTED.toString());

        components.addComponent(orders);
        setContent(components);
    }
}