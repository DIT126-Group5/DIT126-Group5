package com.group05.booksofbliss.model.dao;


import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class BookDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(BookDAO.class, Book.class, Category.class, CategoryDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private BookDAO bookDAO;
    private Book book = new Book("9780007103072", "Bible");
    
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
                .findByISBN(book.getIsbn()).getIsbn()
                ,book.getIsbn());
    }
}
