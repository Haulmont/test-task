package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.AddOrderEvent;
import com.haulmont.testtask.Event.BackButtonEvent;
import com.haulmont.testtask.Event.ChangeOrderEvent;
import com.haulmont.testtask.Event.DeleteOrderEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.View.util.ShortcutUtil;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * Represent add, change, delete buttons for order table
 */
public class ChangeOrdersLayout extends HorizontalLayout {

    private final MainEventBus bus;
    private Order order;

   private Button changeOrder;
   private Button deleteOrder;
   private Button back;


    public ChangeOrdersLayout(MainEventBus bus) {
        this.bus = bus;
        this.bus.register(this);
        init();
    }

    private void init() {
        Button addOrder = new Button("Добавить заказ");
        addOrder.addClickListener(event -> {
            bus.post(new AddOrderEvent());
        });

        addOrder.addStyleName("primary");
        ShortcutUtil.getInstance().setPrimaryShortcut(addOrder);



        changeOrder = new Button("Изменить заказ");
        changeOrder.addClickListener(event -> {
            bus.post(new ChangeOrderEvent(this.order));
        });
        changeOrder.setEnabled(false);


        deleteOrder = new Button("Удалить заказ");
        deleteOrder.addClickListener(event -> {
            bus.post(new DeleteOrderEvent(this.order));
        });

        deleteOrder.setEnabled(false);
        deleteOrder.addStyleName("danger");

        back = new Button("Назад");
        back.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        back.addClickListener(event -> {
            bus.post(new BackButtonEvent());
        });

        addComponents(addOrder, changeOrder, deleteOrder, back);
        this.setComponentAlignment(addOrder, Alignment.BOTTOM_LEFT);
        this.setComponentAlignment(changeOrder, Alignment.BOTTOM_LEFT);
        this.setComponentAlignment(deleteOrder, Alignment.BOTTOM_LEFT);
        this.setComponentAlignment(back, Alignment.BOTTOM_CENTER);
        setMargin(false);
        setSpacing(true);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setChangeEnabled(boolean trueOrFalse) {
        changeOrder.setEnabled(trueOrFalse);
        deleteOrder.setEnabled(trueOrFalse);
    }
}
