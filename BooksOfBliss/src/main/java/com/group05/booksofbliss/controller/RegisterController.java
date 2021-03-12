
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.AccountService;
import com.group05.booksofbliss.view.RegisterBackingBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.javamoney.moneta.Money;

@Named("registerController")
@RequestScoped
public class RegisterController {

    @Inject
    private RegisterBackingBean registerBackingBean;

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private FacesContext facesContext;

    @Inject
    private AccountService accountService;

    public void createAccount() {
        Account acc = new Account(registerBackingBean.getUsername(),
                registerBackingBean.getFirstname(),
                registerBackingBean.getLastname(),
                registerBackingBean.getPhonenumber(),
                registerBackingBean.getEmail(),
                registerBackingBean.getPassword(),
                new Address(registerBackingBean.getStreet(), registerBackingBean.getPostalcode(), registerBackingBean.getCity()),
                Money.of(0, "SEK")
        );
        accountService.setPassword(registerBackingBean.getPassword(), acc);
        accountDAO.create(acc);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Du har skapat ett konto!", null);
        facesContext.addMessage(null, facesMessage);
    }
}
