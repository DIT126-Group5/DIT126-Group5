package com.group05.booksofbliss.model.entity;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookTest {

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

    private Book book;

    @Before
    public void init() {
        book = new Book("34124124124", "Calculus", 2007, "");
    }

    @Test
    public void BlankTitleOrISBNShouldCauseViolation() {
        Book book = new Book("", "", 1996, "");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertTrue(violations.size() == 2);
    }

    @Test
    public void NonBlankTitleOrISBNShouldNotCauseViolation() {
        Book book = new Book("34124124124", "Title", 1996, "");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertTrue(violations.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void setIsbn_givenNull_throwsNPE() {
        book.setIsbn(null);
    }

    @Test(expected = NullPointerException.class)
    public void setTitle_givenNull_throwsNPE() {
        book.setTitle(null);
    }

    @Test(expected = NullPointerException.class)
    public void setImage_givenNull_throwsNPE() {
        book.setImageUrl(null);
    }
}
