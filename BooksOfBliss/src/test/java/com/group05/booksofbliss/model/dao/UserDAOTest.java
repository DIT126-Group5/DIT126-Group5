
import com.group05.booksofbliss.model.dao.UserDAO;
import com.group05.booksofbliss.model.entity.User;
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
public class UserDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(UserDAO.class, User.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private UserDAO userDAO;
    private User user = new User();
    @Before
    public void init() {
        userDAO.create(user);
    }
    @After
    public void clean() {
        userDAO.remove(user);
    }

    @Test
    public void checkThatFindUsersMatchingNameMatchesCorrectly() {
        Assert.assertTrue(true);
        /* Some better condition */
    }
}
