package com.marcuslull.entities.validators;

import com.marcuslull.entities.BaseEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * ValidationChecker is a utility class that provides methods to validate
 * entity objects extending from BaseEntity.
 * This class utilizes the Java Bean Validation (JSR 380) API to perform
 * the validation, ensuring that the entities adhere to their constraint annotations.
 */
public final class ValidationChecker {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    private ValidationChecker() {}

    public static boolean isValidEntity(BaseEntity entity) {
        return validator.validate(entity).isEmpty();
    }
}
