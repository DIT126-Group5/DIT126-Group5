package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.database.dao.key.UserReviewPK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@IdClass(UserReviewPK.class)
public class UserReview implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "reviewer")
    private Account reviewer;

    @Id
    @ManyToOne
    @JoinColumn(name = "reviewee")
    private Account reviewee;
    
    private String comment;
    private int rating;
}
