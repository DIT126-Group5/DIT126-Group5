package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Condition;
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
public class ConditionDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private ConditionDAO dao;

    private Condition condition = new Condition("Legendary");

    @Before
    public void init() {
        dao.create(condition);
    }

    @After
    public void clean() {
        dao.remove(condition);
    }

    @Test
    public void checkInsertedEqualsRetrieved() {
        Assert.assertEquals(condition, dao.find(condition.getName()));
    }
}
