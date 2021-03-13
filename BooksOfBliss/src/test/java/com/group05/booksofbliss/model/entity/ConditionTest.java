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

public class ConditionTest {

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
        Condition condition = new Condition("");
        
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);

        assertTrue(violations.size() == 1);
    }
    
        @Test
    public void NonBlankTitleOrISBNShouldNotCauseViolation() {
        Condition condition = new Condition("Bra");

        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        
        assertTrue(violations.isEmpty());
    }

}
