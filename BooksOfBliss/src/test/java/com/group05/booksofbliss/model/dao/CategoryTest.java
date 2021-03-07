package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
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
public class CategoryTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BookDAO bookDAO;

    @Inject
    private CategoryDAO categoryDAO;

    @Resource
    private UserTransaction trx;

    Category category;
    Category category2;
    List<Category> categories;
    List<Category> categories2;
    Author author;
    Author author2;
    List<Author> authors;
    List<Author> authors2;
    Book book;
    Book book2;

    @Before
    public void init() throws Exception {
        trx.begin();
        category = new Category("Hållbarhet");
        category2 = new Category("Ekonomi");
        categoryDAO.create(category);
        categoryDAO.create(category2);
        categories = new ArrayList<>();
        categories2 = new ArrayList<>();
        categories.add(category);
        categories2.add(category2);

        author = new Author("Håkan Gulliksson");
        author2 = new Author("Ulf Holmgren");
        authorDAO.create(author);
        authorDAO.create(author2);

        authors = new ArrayList<Author>();
        authors2 = new ArrayList<Author>();
        authors.add(author);
        authors.add(author2);
        authors2.add(author2);
        book = new Book("9789144151458", "Hållbar utveckling", 2021, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.adlibris.com%2Fse%2Fbok%2Fhallbar-utveckling---livskvalitet-beteende-och-teknik-9789144151458&psig=AOvVaw0m1oxt_x4E6gVfR-KKcUMB&ust=1614764579235000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCJjEkvuoke8CFQAAAAAdAAAAABAJ");
        book.setAuthors(authors);
        book.setCategories(categories);
        bookDAO.create(book);

        book2 = new Book("9789147096978", "Ansvarsfull verksamhetsstyrning", 2011, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.adlibris.com%2Fse%2Fbok%2Fhallbar-utveckling---livskvalitet-beteende-och-teknik-9789144151458&psig=AOvVaw0m1oxt_x4E6gVfR-KKcUMB&ust=1614764579235000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCJjEkvuoke8CFQAAAAAdAAAAABAJ");
        book2.setAuthors(authors2);
        book2.setCategories(categories2);
        bookDAO.create(book2);
        trx.commit();
    }

    @After
    public void clean() {
        bookDAO.remove(book);
        bookDAO.remove(book2);
        categoryDAO.remove(category);
        categoryDAO.remove(category2);
        authorDAO.remove(author);
        authorDAO.remove(author2);
    }

    @Test
    public void checkThatFindByNameWorksCorrectly() {
        Assert.assertEquals(category, categoryDAO.findByName(category.getName()));
        Assert.assertEquals(category2, categoryDAO.findByName(category2.getName()));
    }
}
