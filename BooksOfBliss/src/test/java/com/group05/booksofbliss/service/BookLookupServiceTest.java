package com.group05.booksofbliss.service;

import com.group05.booksofbliss.Deployments;
import java.io.IOException;
import javax.inject.Inject;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BookLookupServiceTest {

    @Inject
    private BookLookupService lookupService;

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    @Test
    public void testValidIsbn() throws IOException, InterruptedException {
        BookLookupService.LookupResult result = lookupService.lookupByIsbn("9780134092669");

        assertTrue(result.isValid());
        assertEquals("Computer Systems", result.getTitle());
        assertEquals(2015, result.getPublishYear());
        assertEquals("http://books.google.com/books/content?id=7-IaogEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", result.getImageUrl());
        assertArrayEquals(new String[]{"Randal E. Bryant", "David R. O'Hallaron"}, result.getAuthors().toArray());
        assertArrayEquals(new String[]{"Computers"}, result.getCategories().toArray());
    }

    @Test
    public void testInvalidIsbn() throws IOException, InterruptedException {
        BookLookupService.LookupResult result = lookupService.lookupByIsbn("gherogherog");

        assertNull(result);
    }

    @Test
    public void testResultHashcodeAndEqualsCorrect() {
        EqualsVerifier.forClass(BookLookupService.LookupResult.class).verify();
    }
}
