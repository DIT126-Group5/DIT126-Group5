package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.service.BankService;
import com.group05.booksofbliss.security.Auth;
import com.group05.booksofbliss.view.BuyCreditsBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import org.javamoney.moneta.Money;

@Data
@Named("buyCreditsController")
@RequestScoped
public class BuyCreditsController implements Serializable {

    @Inject
    private BuyCreditsBackingBean backingBean;

    @Inject
    private BankService bankService;
    @Inject
    private Auth auth;

    public String confirmPurchase() {
        if (auth.getAccount() == null) {
            return null;
        }

        bankService.buyCredits(auth.getAccount(), Money.of(backingBean.getAmount(), "SEK"));

        return "success";
    }
}
