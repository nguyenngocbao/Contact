package com.safetrust.interview.contact.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    private static final String NUMBER_MATCHES = "[0-9]+";
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 14;
    

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {
        return contactField != null && contactField.matches(NUMBER_MATCHES)
                && (contactField.length() > MIN_LENGTH) && (contactField.length() < MAX_LENGTH);
    }

}
