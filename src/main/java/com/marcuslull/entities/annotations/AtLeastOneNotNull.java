package com.marcuslull.entities.annotations;

import com.marcuslull.entities.validators.NoteNullForeignKeyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoteNullForeignKeyValidator.class)
@Documented
public @interface AtLeastOneNotNull {
    String message() default "At least one of category, supplier, or product must be specified";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
