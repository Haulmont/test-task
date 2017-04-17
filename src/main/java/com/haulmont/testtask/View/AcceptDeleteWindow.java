package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.AcceptDeleteEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.View.util.ShortcutUtil;
import com.vaadin.ui.*;

/**
 * Created by Cok on 16.04.2017.
 */
public class AcceptDeleteWindow extends Window {

    private final MainEventBus bus;
    private final Object objectToDelete;

    public AcceptDeleteWindow(MainEventBus mainEventBus, Object objectToDelete) {
        super("Подтверждение удаления");
        setModal(true);
        center();
        setWidth("25%");
        setHeight("25%");
        this.objectToDelete = objectToDelete;
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

        Label areYouSure = new Label("Вы действительно хотите удалить?");

        Button delete = new Button("Удалить", listener -> {
            bus.post(new AcceptDeleteEvent(objectToDelete));
            close();
        });

        delete.addStyleName("primary");
        ShortcutUtil.getInstance().setPrimaryShortcut(delete);

        Button cancel = new Button("Отменить", listener -> {
            close();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        buttons.addComponents(delete, cancel);

        verticalLayout.addComponents(areYouSure, buttons);
        return verticalLayout;
    }


}
