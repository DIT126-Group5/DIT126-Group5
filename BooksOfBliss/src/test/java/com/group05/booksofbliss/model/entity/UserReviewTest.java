package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.entity.attribute.Address;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.javamoney.moneta.Money;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserReviewTest {

    private UserReview userReview;

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

    @Before
    public void init() {
        userReview = new UserReview(
                new Account("anotheruser", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street534", "345435", "gergehger"), Money.of(50, "SEK")),
                new Account("user", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street534", "345435", "gergehger"), Money.of(50, "SEK")),
                "Very nice book!",
                3);
    }

    @Test
    public void ratingTooLowCausesViolation() {
        userReview.setRating(0);

        assertTrue(validator.validate(userReview).size() == 1);
    }

    @Test
    public void ratingTooHighCausesViolation() {
        userReview.setRating(6);

        assertTrue(validator.validate(userReview).size() == 1);
    }

    @Test
    public void ratingInBoundsCausesNoViolation() {
        userReview.setRating(3);

        assertTrue(validator.validate(userReview).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void setReviewer_givenNull_throwsNPE() {
        userReview.setReviewer(null);
    }

    @Test(expected = NullPointerException.class)
    public void setReviewee_givenNull_throwsNPE() {
        userReview.setReviewee(null);
    }

    @Test(expected = NullPointerException.class)
    public void setComment_givenNull_throwsNPE() {
        userReview.setComment(null);
    }

}
