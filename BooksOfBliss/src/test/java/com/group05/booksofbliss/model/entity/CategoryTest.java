package com.group05.booksofbliss.model.entity;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.*;
import org.junit.Test;

public class CategoryTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolationsIfNonBlank() {
        Category category = new Category("Mathematics");

        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        assertTrue(violations.isEmpty());
    }
    
        @Test
    public void shouldHaveViolationsIfBlank() {
        Category category = new Category("");

        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        
        assertTrue(violations.size() == 1);
    }

}
