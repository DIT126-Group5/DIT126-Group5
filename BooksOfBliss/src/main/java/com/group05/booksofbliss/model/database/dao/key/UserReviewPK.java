package com.group05.booksofbliss.model.database.dao.key;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserReviewPK implements Serializable {
    private String reviewer;
    private String reviewee;
}
