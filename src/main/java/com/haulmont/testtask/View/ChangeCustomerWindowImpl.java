package com.haulmont.testtask.View;

import com.haulmont.testtask.Event.SaveCustomerEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.View.util.ShortcutUtil;
import com.haulmont.testtask.View.validator.PhoneValidator;
import com.haulmont.testtask.View.validator.StringValidator;
import com.vaadin.data.Validator;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cok on 16.04.2017.
 */
public class ChangeCustomerWindowImpl extends Window implements CustomerWindow {
    private final MainEventBus bus;
    private Customer customer;

    public ChangeCustomerWindowImpl(MainEventBus mainEventBus, Customer customer) {
        super("Редактирование клиента");
        setModal(true);
        center();
        setWidth("30%");
        setHeight("70%");
        this.customer = customer;
        Layout layout = initLayout();
        this.bus = mainEventBus;
        this.bus.register(this);
        setContent(layout);
    }

    private Layout initLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(true);
        verticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        TextField firstName = new TextField("Имя");
        firstName.setValue(this.customer.getFirstName());
        firstName.addValidator(new StringValidator("Имя должно содержать только буквы"));
        TextField lastName = new TextField("Фамилия");
        lastName.setValue(this.customer.getThirdName());
        lastName.addValidator(new StringValidator("Фамилия должна содержать только буквы"));
        TextField thirdName = new TextField("Отчетство");
        thirdName.setValue(this.customer.getLastName());
        thirdName.addValidator(new StringValidator("Отчество должно содержать только буквы"));
        TextField phone = new TextField("Телефон");
        phone.setValue(String.valueOf(this.customer.getPhone()));
        phone.addValidator(new PhoneValidator("Некорректный номер телефона"));

        List<TextField> fields = Arrays.asList(firstName, lastName, thirdName, phone);


        Button save = new Button("ОК", listener -> {
            try {
                fields.forEach(AbstractField::validate);
                this.customer.setFirstName(firstName.getValue());
                this.customer.setLastName(lastName.getValue());
                this.customer.setThirdName(thirdName.getValue());
                this.customer.setPhone(phone.getValue());
                bus.post(new SaveCustomerEvent(this.customer));
                close();
            } catch (Validator.InvalidValueException e) {
                Notification.show(e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        save.addStyleName("primary");
        ShortcutUtil.getInstance().setPrimaryShortcut(save);

        Button cancel = new Button("Отменить", listener -> {
            close();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        buttons.addComponents(save, cancel);

        verticalLayout.addComponents(firstName, lastName, thirdName, phone, buttons);
        verticalLayout.setSizeFull();
        return verticalLayout;
    }
}
