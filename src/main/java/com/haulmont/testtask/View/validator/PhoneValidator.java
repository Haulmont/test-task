package com.haulmont.testtask.View.validator;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;

/**
 * Russian phone number format validator
 */
public class PhoneValidator extends AbstractValidator<String> implements Validator {
    public PhoneValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String value) {
//        +79855310868
//        +79855310868
//        880084545454
//        88008454545411
//        465456465465
//        784545487878
//        are supported
        return value.matches("^((\\+7|7|8)+([0-9]){10})$");
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
