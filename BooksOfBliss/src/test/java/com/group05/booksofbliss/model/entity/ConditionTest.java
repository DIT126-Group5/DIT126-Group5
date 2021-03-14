package com.group05.booksofbliss.model.entity;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
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

    @Test(expected = NullPointerException.class)
    public void setName_givenNull_throwsNPE() {
        new Condition("Bra").setName(null);
    }
}
