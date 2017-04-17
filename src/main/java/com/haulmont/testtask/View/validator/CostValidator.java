package com.haulmont.testtask.View.validator;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;

/**
 * Assert that cost of order is greater or equal for 0.0d
 */
public class CostValidator extends AbstractValidator<String> implements Validator {
    public CostValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String value) {
        try {
            Double cost = Double.valueOf(value);
            return 0.0d <= cost;
        } catch (NumberFormatException incorrectNumber) {
            return false;
        }

    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
