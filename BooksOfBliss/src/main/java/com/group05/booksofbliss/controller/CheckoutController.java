package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.OrderService;
import com.group05.booksofbliss.security.Auth;
import com.group05.booksofbliss.view.CheckoutBackingBean;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("checkoutController")
@RequestScoped
public class CheckoutController implements Serializable {

    @Inject
    private CheckoutBackingBean checkoutBackingBean;

    @Inject
    private OrderService orderService;
    @Inject
    private Auth auth;

    @Inject
    private ExternalContext externalContext;

    public void confirmPurchase() throws IOException {
        if (checkoutBackingBean.getListing() == null || auth.getAccount() == null) {
            return;
        }

        orderService.orderListing(checkoutBackingBean.getListing(),
                auth.getAccount(),
                new Address(checkoutBackingBean.getStreet(),
                        checkoutBackingBean.getPostalCode(),
                        checkoutBackingBean.getCity()));

        // Redirect to checkout success/review page
        externalContext.redirect(externalContext.getRequestContextPath() + "/checkout_success/" + checkoutBackingBean.getListing().getId());
    }
}
