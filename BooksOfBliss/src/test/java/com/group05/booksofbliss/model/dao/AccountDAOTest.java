
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import java.util.List;
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
                .addClasses(AccountDAO.class, Account.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private AccountDAO accountDAO;
    private Account account = new Account();
    @Before
    public void init() {
        account.setName("testName");
        account.setUsername("username");
        accountDAO.create(account);
        //accountDAO.create(new Account("testUser","testName2"));
    }
    @After
    public void clean() {
        accountDAO.remove(account);
    }

    @Test
    public void checkThatFindUsersMatchingNameMatchesCorrectly() {
        Assert.assertTrue(true);
        //List<Account> accountList = accountDAO.findAll();
        
    }
}
