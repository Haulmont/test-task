package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.AddCustomerEvent;
import com.haulmont.testtask.Event.BackButtonEvent;
import com.haulmont.testtask.Event.ChangeCustomerEvent;
import com.haulmont.testtask.Event.DeleteCustomerEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.View.util.ShortcutUtil;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * Represent adding, changing, deleting buttons in customer view
 */
public class ChangeCustomerLayout extends HorizontalLayout implements Layout {
    private final MainEventBus bus;

    private Button changeCustomer;
    private Button deleteCustomer;


    public ChangeCustomerLayout(MainEventBus bus) {
        this.bus = bus;
        this.bus.register(this);
        init();
    }

    private void init() {
        Button addCustomer = new Button("Добавить клиента");
        addCustomer.addClickListener(event -> {
            bus.post(new AddCustomerEvent());
        });

        addCustomer.addStyleName("primary");
        ShortcutUtil.getInstance().setPrimaryShortcut(addCustomer);


        changeCustomer = new Button("Изменить клиента");
        changeCustomer.addClickListener(event -> {
            bus.post(new ChangeCustomerEvent());
        });
        changeCustomer.setEnabled(false);


        deleteCustomer = new Button("Удалить клиента");
        deleteCustomer.setClickShortcut(ShortcutAction.KeyCode.DELETE);
        deleteCustomer.addClickListener(event -> {
            bus.post(new DeleteCustomerEvent());
        });

        deleteCustomer.setEnabled(false);
        deleteCustomer.addStyleName("danger");

        Button back = new Button("Назад");
        back.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        back.addClickListener(event -> {
            bus.post(new BackButtonEvent());
        });

        addComponents(addCustomer, changeCustomer, deleteCustomer, back);
        this.setComponentAlignment(addCustomer, Alignment.BOTTOM_LEFT);
        this.setComponentAlignment(changeCustomer, Alignment.BOTTOM_LEFT);
        this.setComponentAlignment(deleteCustomer, Alignment.BOTTOM_LEFT);
        this.setComponentAlignment(back, Alignment.BOTTOM_CENTER);
        setMargin(true);
        setSpacing(true);
    }

    public void setChangeEnabled(boolean trueOrFalse) {
        changeCustomer.setEnabled(trueOrFalse);
        deleteCustomer.setEnabled(trueOrFalse);
    }
}
