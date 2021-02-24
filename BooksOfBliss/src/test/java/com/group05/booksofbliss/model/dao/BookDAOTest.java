package com.group05.booksofbliss.model.dao;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BookDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private BookDAO bookDAO;

    //private Book book = new Book("9780007103072", "Bible", Arrays.asList(new Author("AuthorName")),
    //        Arrays.asList(new Category("")));

    /*
    @Before
    public void init() {
        bookDAO.create(book);
        //accountDAO.create(new Account("testUser","testName2"));
    }

    @After
    public void clean() {
        bookDAO.remove(book);
    }

    @Test
    public void checkThatFindUsersMatchingNameMatchesCorrectly() {
        Assert.assertEquals(bookDAO
                .findByISBN(book.getIsbn()).getIsbn(),
                book.getIsbn());
    }
     */
    @Test
    public void checkThatFindUsersMatchingNameMatchesCorrectly() {
        //not yet implemented
    }
}
