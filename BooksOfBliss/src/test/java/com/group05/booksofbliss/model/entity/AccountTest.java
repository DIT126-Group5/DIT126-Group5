
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private Account account;

    @Before
    public void init() {
        account = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
    }

    @Test(expected = NullPointerException.class)
    public void setUsername_givenNull_throwsNPE() {
        account.setUsername(null);
    }

    @Test(expected = NullPointerException.class)
    public void setFirstname_givenNull_throwsNPE() {
        account.setFirstname(null);
    }

    @Test(expected = NullPointerException.class)
    public void setLastname_givenNull_throwsNPE() {
        account.setLastname(null);
    }

    @Test(expected = NullPointerException.class)
    public void setPhonenumber_givenNull_throwsNPE() {
        account.setPhonenumber(null);
    }

    @Test(expected = NullPointerException.class)
    public void setEmail_givenNull_throwsNPE() {
        account.setEmail(null);
    }

    @Test(expected = NullPointerException.class)
    public void setPassword_givenNull_throwsNPE() {
        account.setPassword(null);
    }

    @Test(expected = NullPointerException.class)
    public void setAddress_givenNull_throwsNPE() {
        account.setAddress(null);
    }

    @Test(expected = NullPointerException.class)
    public void setBalance_givenNull_throwsNPE() {
        account.setBalance(null);
    }
}
