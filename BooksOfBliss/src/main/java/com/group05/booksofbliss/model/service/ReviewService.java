package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.model.dao.UserReviewDAO;
import com.group05.booksofbliss.model.database.dao.key.UserReviewPK;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Purchase;
import com.group05.booksofbliss.model.entity.UserReview;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ReviewService implements Serializable {

    @Inject
    private UserReviewDAO userReviewDAO;

    @Transactional
    public void reviewPurchase(Purchase purchase, String comment, int rating) {
        if (rating < 1 || rating > 5){
            throw new IllegalArgumentException("Rating måste vara mellan 1-5.");
        }
        Account reviewer = purchase.getAccount();
        Account reviewee = purchase.getListing().getPublishedBy();

        UserReview previousReview = userReviewDAO.find(new UserReviewPK(reviewer.getUsername(), reviewee.getUsername()));
        if (previousReview != null) {  // If reviewer has previously reviewed reviewee, update with new comment and rating
            previousReview.setRating(rating);
            previousReview.setComment(comment);
            userReviewDAO.update(previousReview);
        } else {
            userReviewDAO.create(new UserReview(reviewer, reviewee, comment, rating));
        }
    }
}
