
import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import javax.inject.Inject;
import org.javamoney.moneta.Money;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
        return Deployments.defaultArchive();
    }

    @Inject
    private AccountDAO accountDAO;

    private Account account = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));

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
