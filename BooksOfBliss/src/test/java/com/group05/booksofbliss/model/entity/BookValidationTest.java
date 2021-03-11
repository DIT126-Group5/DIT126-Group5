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

public class BookValidationTest {

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
    public void BlankTitleOrISBNShouldCauseViolation() {
        Book book = new Book("", "", 1996, "");
        
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertTrue(violations.size() == 2);
    }
    
        @Test
    public void NonBlankTitleOrISBNShouldNotCauseViolation() {
        Category category = new Category("");

        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        
        assertTrue(violations.size() == 1);
    }

}
