package com.haulmont.testtask.View.validator;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;

/**
 * Assert that input string contains only characters without any digest
 */
public class StringValidator extends AbstractValidator<String> implements Validator {
    public StringValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String value) {
        return value.matches("^[a-zA-Z-а-яА-Я\\s]*$");
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
