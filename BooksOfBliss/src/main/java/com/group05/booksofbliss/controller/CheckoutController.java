package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.OrderService;
import com.group05.booksofbliss.security.Auth;
import com.group05.booksofbliss.view.CheckoutBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

@Data
@Named("checkoutController")
@RequestScoped
public class CheckoutController implements Serializable {

    @Inject
    private CheckoutBackingBean checkoutBackingBean;

    @Inject
    private OrderService orderService;
    @Inject
    private Auth auth;

    public String confirmPurchase() {
        if (checkoutBackingBean.getListing() == null || auth.getAccount() == null) {
            return null;
        }

        orderService.orderListing(checkoutBackingBean.getListing(),
                auth.getAccount(),
                new Address(checkoutBackingBean.getStreet(),
                        checkoutBackingBean.getPostalCode(),
                        checkoutBackingBean.getCity()));

        return "success";
    }
}
