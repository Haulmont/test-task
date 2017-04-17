package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.SaveNewOrderEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.View.util.ShortcutUtil;
import com.haulmont.testtask.View.validator.CostValidator;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

/**
 * Window for adding order
 * <p>Contains all required fields of order entity</p>
 */
public class AddOrderWindowImpl extends Window implements OrderWindow {


    private final MainEventBus bus;
    private List<Customer> customers;

    private ComboBox customer;

    public AddOrderWindowImpl(List<Customer> customerList, MainEventBus bus) {
        super("Добавление заказа");
        setModal(true);
        center();
        setWidth("30%");
        setHeight("90%");
        this.customers = customerList;
        Layout layout = initLayout();
        this.bus = bus;
        this.bus.register(this);
        setContent(layout);

    }

    private Layout initLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(true);
        verticalLayout.setSizeFull();
        TextArea description = new TextArea("Описание");
        description.addValidator(new StringLengthValidator("Описание должно содержать 1-300 символов", 1, 300, false));
        description.setSizeFull();
        verticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        customer = new ComboBox("Клиент", customers);
        customer.setFilteringMode(FilteringMode.CONTAINS);
        customer.addValidator(new NullValidator("Выберите клиента", false));
        DateField createDate = new DateField("Дата создания");
        createDate.setResolution(Resolution.MINUTE);
        DateField closeDate = new DateField("Дата окончания работ");
        closeDate.setResolution(Resolution.MINUTE);

        TextField cost = new TextField("Стоимость");
        cost.addValidator(new CostValidator("Стоимость должа быть больше, либо равна 0"));
        ComboBox status = new ComboBox("Статус");
        status.addItems((Object[]) OrderStatus.values());
        status.addValidator(new NullValidator("Выберите статус", false));

        List<? extends AbstractField<?>> fields = Arrays.asList(cost, customer, description, status);
        Button save = new Button("ОК", listener -> {
            try {
                fields.forEach(AbstractField::validate);
                Order order = new Order();
                Customer customerValue = getCustomerFromBox();
                order.setCustomer(customerValue);
                order.setDescription(description.getValue());
                order.setCost(Double.valueOf(cost.getValue()));
                order.setCreateDate(createDate.getValue());
                if (closeDate.getValue() != null) order.setCloseDate(closeDate.getValue());
                order.setStatus((OrderStatus)status.getValue());
                bus.post(new SaveNewOrderEvent(order));
                close();
            } catch (Validator.InvalidValueException e) {
                 Notification.show(e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        save.addStyleName("primary");
        ShortcutUtil.getInstance().setPrimaryShortcut(save);

        Button cancel = new Button("Отменить", listener -> close());


        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(save, cancel);
        buttons.setSpacing(true);

        verticalLayout.addComponents(description, customer, createDate, closeDate, cost, status, buttons);

        return verticalLayout;
    }

    private Customer getCustomerFromBox() {
            Object customer = this.customer.getValue();
            return (Customer) customer;
    }

}
