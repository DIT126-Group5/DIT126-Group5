package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.OrderService;
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
    private AccountDAO accountDAO;

    public String confirmPurchase() {
        if (checkoutBackingBean.getListing() == null) {
            return null;
        }

        orderService.orderListing(checkoutBackingBean.getListing(),
                accountDAO.findAll().get(0),
                new Address(checkoutBackingBean.getStreet(),
                        checkoutBackingBean.getPostalCode(),
                        checkoutBackingBean.getCity()));

        return "success";
    }
}
