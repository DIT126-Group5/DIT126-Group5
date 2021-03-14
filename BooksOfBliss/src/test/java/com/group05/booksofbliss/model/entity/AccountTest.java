package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.entity.attribute.Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.javamoney.moneta.Money;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountTest {

    private Account account;

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
        account = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
    }

    @Test(expected = NullPointerException.class)
    public void setUsername_givenNull_throwsNPE() {
        account.setUsername(null);
    }

    @Test(expected = NullPointerException.class)
    public void setFirstname_givenNull_throwsNPE() {
        account.setFirstname(null);
    }

    @Test(expected = NullPointerException.class)
    public void setLastname_givenNull_throwsNPE() {
        account.setLastname(null);
    }

    @Test(expected = NullPointerException.class)
    public void setPhonenumber_givenNull_throwsNPE() {
        account.setPhonenumber(null);
    }

    @Test(expected = NullPointerException.class)
    public void setEmail_givenNull_throwsNPE() {
        account.setEmail(null);
    }

    @Test(expected = NullPointerException.class)
    public void setPassword_givenNull_throwsNPE() {
        account.setPassword(null);
    }

    @Test(expected = NullPointerException.class)
    public void setAddress_givenNull_throwsNPE() {
        account.setAddress(null);
    }

    @Test(expected = NullPointerException.class)
    public void setBalance_givenNull_throwsNPE() {
        account.setBalance(null);
    }

    @Test
    public void setUsernameTest() {
        //check if the username is set correctly
        account.setUsername("something");
        assertEquals("something", account.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUsernameTest_invalidUsername_throwsIllegalArgumentException() {
        account.setUsername("some");
    }

    @Test
    public void isValidUsernameTest() {
        //given too short username:
        Account badUsernameAccount = new Account("user", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));

        Set<ConstraintViolation<Account>> violations = validator.validate(badUsernameAccount);
        //then:
        assertEquals(violations.size(), 1);
        assertFalse(account.isValidUsername("ThisUsernameIsWaaaaaaayTooLongToBeUsed"));
        assertFalse(account.isValidUsername("ONLYCAPS"));
    }

    @Test
    public void isValidPasswordTest() {
        assertTrue(account.isValidPassword("Password123!"));
        assertFalse(account.isValidPassword("Pa1!"));
        assertFalse(account.isValidPassword("Password1!Password1!Password1!Password1!Password1!Password1!Password1!Password1!Password1!Password1!Password1!"));
        assertFalse(account.isValidPassword("Password!!!"));
        assertFalse(account.isValidPassword("PASSWORD123!"));
        assertFalse(account.isValidPassword("password123!"));
    }

    @Test
    public void withdrawFromBalanceTest() {
        account.withdrawFromBalance(Money.of(10, "SEK"));
        assertEquals(Money.of(0, "SEK"), account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawFromBalanceTest_whenNegativeAmount_throwsIllegalArgumentException() {
        account.withdrawFromBalance(Money.of(-150, "SEK"));
    }

    @Test(expected = IllegalStateException.class)
    public void withdrawFromBalanceTest_whenBalanceIsLow_throwsIllegalStateException() {
        account.withdrawFromBalance(Money.of(500, "SEK"));
    }

    @Test
    public void addToBalanceTest() {
        account.addToBalance(Money.of(500, "SEK"));
        assertEquals(Money.of(510, "SEK"), account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToBalanceTest_whenNegativeAmount_throwsIllegalArgumentException() {
        account.addToBalance(Money.of(-500, "SEK"));
    }

    @Test
    public void checkValidEmailTest() {
        //given invalid email
        Account accountWithBadEmail = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));

        //when:
        Set<ConstraintViolation<Account>> violations
                = validator.validate(accountWithBadEmail);

        //then:
        assertEquals(violations.size(), 1);
    }

    @Test
    public void reviewTest() {
        Account reviewer = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
        Account reviewee = new Account("reviewee", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));

        List<UserReview> userReviews = new ArrayList<>();
        UserReview userReview1 = new UserReview(reviewer, reviewee, "", 2);
        UserReview userReview2 = new UserReview(reviewer, reviewee, "", 5);

        userReviews.add(userReview1);
        userReviews.add(userReview2);

        reviewee.setReviewsReceived(userReviews);

        assertEquals(3.5, reviewee.getReputation(), 0.1);
    }

    @Test
    public void shouldHaveNoViolations() {
        //when:
        Set<ConstraintViolation<Account>> violations
                = validator.validate(account);

        //then:
        assertTrue(violations.isEmpty());
    }

}
