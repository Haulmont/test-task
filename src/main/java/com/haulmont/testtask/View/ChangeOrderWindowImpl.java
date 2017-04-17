package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.SaveOrderEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.View.util.ShortcutUtil;
import com.haulmont.testtask.View.validator.CostValidator;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cok on 17.04.2017.
 */
public class ChangeOrderWindowImpl extends Window implements OrderWindow {
    private final Order order;
    private final MainEventBus bus;

    public ChangeOrderWindowImpl(MainEventBus mainEventBus, Order order) {
        super("Редактирование заказа");
        setModal(true);
        center();
        setWidth("30%");
        setHeight("90%");
        this.order = order;
        Layout layout = initLayout();
        this.bus = mainEventBus;
        this.bus.register(this);
        setContent(layout);
    }

    private Layout initLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(true);
        verticalLayout.setSizeFull();
        verticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        TextArea description = new TextArea("Описание");
        description.addValidator(new StringLengthValidator("Описание должно содержать 1-300 символов", 1, 300, false));
        description.setSizeFull();

        ComboBox customer = new ComboBox("Клиент");
        customer.addItem(order.getCustomer());
        customer.select(order.getCustomer());
        customer.setEnabled(false);

        DateField createDate = new DateField("Дата создания");
        createDate.setResolution(Resolution.MINUTE);

        DateField closeDate = new DateField("Дата окончания работ");
        closeDate.setResolution(Resolution.MINUTE);

        TextField cost = new TextField("Стоимость");
        cost.addValidator(new CostValidator("Стоимость должа быть больше, либо равна 0"));

        ComboBox status = new ComboBox("Статус");
        status.addItems((Object[]) OrderStatus.values());
        status.addValidator(new NullValidator("Выберите статус", false));

        description.setValue(order.getDescription());
        createDate.setValue(order.getCreateDate());
        closeDate.setValue(order.getCloseDate());
        cost.setValue(String.valueOf(order.getCost()));
        status.select(order.getStatus());

        List<? extends AbstractField<?>> fields = Arrays.asList(cost, customer, description, status);
        Button save = new Button("ОК", listener -> {
            try {
                fields.forEach(AbstractField::validate);
                order.setDescription(description.getValue());
                order.setCost(Double.valueOf(cost.getValue()));
                order.setCreateDate(createDate.getValue());
                if (closeDate.getValue() != null) order.setCloseDate(closeDate.getValue());
                order.setStatus((OrderStatus)status.getValue());
                bus.post(new SaveOrderEvent(order));
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
}
