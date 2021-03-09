
import com.group05.booksofbliss.model.entity.Account;

@RunWith(Arquillian.class)
public class AccountTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
