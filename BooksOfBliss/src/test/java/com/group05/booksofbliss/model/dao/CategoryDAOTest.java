package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.entity.Category;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CategoryDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    @Inject
    private CategoryDAO categoryDAO;

    @Resource
    private UserTransaction trx;

    private Category category;
    private Category category2;

    @Before
    public void init() throws Exception {
        trx.begin();
        category = new Category("Hållbarhet");
        category2 = new Category("Ekonomi");
        categoryDAO.create(category);
        categoryDAO.create(category2);
        trx.commit();
    }

    @After
    public void clean() {
        categoryDAO.remove(category);
        categoryDAO.remove(category2);
    }

    @Test
    public void checkThatFindByNameWorksCorrectly() {
        Assert.assertEquals(category, categoryDAO.find(category.getName()));
        Assert.assertEquals(category2, categoryDAO.find(category2.getName()));
    }
}
