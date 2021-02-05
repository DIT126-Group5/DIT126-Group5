package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.database.dao.key.UserReviewPK;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserReview implements Serializable {
    @EmbeddedId
    UserReviewPK id;
    
    @ManyToOne
    @MapsId("reviewer")
    @JoinColumn(name = "reviewer")
    private Account reviewer;

    @ManyToOne
    @MapsId("reviewee")
    @JoinColumn(name = "reviewee")
    private Account reviewee;
    
//    private String comment;
//    private int rating;
}
