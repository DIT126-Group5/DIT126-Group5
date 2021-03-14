package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.service.ReviewService;
import com.group05.booksofbliss.security.Auth;
import com.group05.booksofbliss.view.ReviewBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("reviewController")
@RequestScoped
public class ReviewController implements Serializable {

    @Inject
    private ReviewBackingBean reviewBackingBean;

    @Inject
    private ReviewService reviewService;
    @Inject
    private Auth auth;

    public String sendReview() {
        if (reviewBackingBean.getListing() == null // No valid listing
                || reviewBackingBean.getListing().getPurchase() == null // Listing not purchased
                || auth.getAccount() == null // Not logged in
                || !auth.getAccount().equals(reviewBackingBean.getListing().getPurchase().getAccount()) // Listing not purchased by logged in user
                ) {
            return null;
        }

        reviewService.reviewPurchase(
                reviewBackingBean.getListing().getPurchase(),
                reviewBackingBean.getComment(),
                reviewBackingBean.getRating());

        return "review_created";
    }
}
