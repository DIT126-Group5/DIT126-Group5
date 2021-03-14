package com.group05.booksofbliss.model.entity.attribute;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressTest {

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
    public void validAddressCausesNoViolation() {
        Address address = new Address("Street534", "345435", "gergehger");
        assertTrue(validator.validate(address).isEmpty());
    }

    @Test
    public void blankStreetCausesViolation() {
        Address address = new Address("", "345435", "gergehger");
        assertTrue(validator.validate(address).size() == 1);
    }

    @Test
    public void blankPostalCodeCausesViolation() {
        Address address = new Address("Street", "", "gergehger");
        assertTrue(validator.validate(address).size() == 1);
    }

    @Test
    public void blankCityCausesViolation() {
        Address address = new Address("Street", "534653", "");
        assertTrue(validator.validate(address).size() == 1);
    }

    @Test(expected = NullPointerException.class)
    public void setStreet_givenNull_throwsNPE() {
        new Address("Street534", "345435", "gergehger").setStreet(null);
    }

    @Test(expected = NullPointerException.class)
    public void setPostalCode_givenNull_throwsNPE() {
        new Address("Street534", "345435", "gergehger").setPostalCode(null);
    }

    @Test(expected = NullPointerException.class)
    public void setCity_givenNull_throwsNPE() {
        new Address("Street534", "345435", "gergehger").setCity(null);
    }

}
