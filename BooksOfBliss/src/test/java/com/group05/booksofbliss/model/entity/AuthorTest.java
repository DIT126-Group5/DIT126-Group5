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

public class AuthorTest {

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
    public void BlankNameShouldCauseViolation() {
        Author author = new Author("");

        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        assertTrue(violations.size() == 1);
    }

    @Test
    public void NonBlankNameShouldNotCauseViolation() {
        Author author = new Author("Jonas");

        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        assertTrue(violations.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void setName_givenNull_throwsNPE() {
        new Author("Jonas").setName(null);
    }
}
