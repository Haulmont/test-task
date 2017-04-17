package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.ShowCustomerEvent;
import com.haulmont.testtask.Event.ShowOrderEvent;
import com.haulmont.testtask.MainEventBus;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Implementation of MainView with Vaadin framework
 */
@Theme(ValoTheme.THEME_NAME)
public class MainViewVaadin extends HorizontalLayout implements MainView {

    private UI mainUI;
    private final MainEventBus eventBus;


    public MainViewVaadin(UI mainUI, MainEventBus eventBus) {
        this.mainUI = mainUI;
        this.eventBus = eventBus;
        this.eventBus.register(this);
        show();
    }


    @Override
    public void show() {
        this.removeAllComponents();
        setSizeFull();
        Button showOrdersBtn = new Button("Посмотреть список заказов");
        showOrdersBtn.addClickListener(event -> {
            eventBus.post(new ShowOrderEvent());
        });
        showOrdersBtn.setClickShortcut(ShortcutAction.KeyCode.ARROW_LEFT, ShortcutAction.ModifierKey.CTRL);

        Button showCustomersBtn = new Button("Посмотреть список клиентов");
        showCustomersBtn.addClickListener(event -> {
            eventBus.post(new ShowCustomerEvent());
        });

        showCustomersBtn.setClickShortcut(ShortcutAction.KeyCode.ARROW_RIGHT, ShortcutAction.ModifierKey.CTRL);


        setMargin(false);
        this.addComponents(showOrdersBtn, showCustomersBtn);
        this.setComponentAlignment(showOrdersBtn, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(showCustomersBtn, Alignment.MIDDLE_CENTER);
        mainUI.setContent(this);
    }
}
