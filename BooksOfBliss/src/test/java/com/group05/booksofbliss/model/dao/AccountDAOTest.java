
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
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
public class AccountDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private AccountDAO accountDAO;

    private Account account = new Account("firstName", "lastName", "username", "Password123!", "Sannegarden", 500.0);

    @Before
    public void init() {
        accountDAO.create(account);
    }

    @After
    public void clean() {
        accountDAO.remove(account);
    }

    //Checks if the Username is correct.
    @Test
    public void checkThatFindUsersMatchingNameMatchesCorrectly() {
        Assert.assertEquals(accountDAO
                .findByUsername(account.getUsername()).getUsername(),
                account.getUsername());
    }
}
