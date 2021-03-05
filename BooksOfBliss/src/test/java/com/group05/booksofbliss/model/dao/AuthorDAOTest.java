package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Author;
import javax.inject.Inject;
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
public class AuthorDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AuthorDAO authorDAO;

    Author author;
    Author author2;
    
    @Before
    public void init() {     
        author = new Author("Håkan Gulliksson");
        author2 = new Author("Ulf Holmgren");
        authorDAO.create(author);
        authorDAO.create(author2);
    }

    @After
    public void clean() {
        authorDAO.remove(author);
        authorDAO.remove(author2);
    }

    @Test
    public void checkThatFindByNameWorksCorrectly() {
        Assert.assertEquals(author, authorDAO.findByName(author.getName()));
        Assert.assertEquals(author2, authorDAO.findByName(author2.getName()));
    }
}