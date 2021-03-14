package com.group05.booksofbliss.model.database.dao.key;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserReviewPKTest {

    @Test
    public void testGettersAndSettersCorrect() {
        new BeanTester().testBean(UserReviewPK.class);
    }

    @Test
    public void testHashcodeAndEqualsCorrect() {
        EqualsVerifier.forClass(UserReviewPK.class).verify();
    }
}
