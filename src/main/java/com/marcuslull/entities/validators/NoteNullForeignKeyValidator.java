package com.marcuslull.entities.validators;

import com.marcuslull.entities.Note;
import com.marcuslull.entities.annotations.AtLeastOneNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoteNullForeignKeyValidator implements ConstraintValidator<AtLeastOneNotNull, Note> {

    @Override
    public boolean isValid(Note note, ConstraintValidatorContext context) {
        return note.getCategory() != null || note.getSupplier() != null || note.getProduct() != null;
    }

    @Override
    public void initialize(AtLeastOneNotNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
