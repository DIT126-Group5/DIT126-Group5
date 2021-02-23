package com.group05.booksofbliss.model.database.dao.key;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserReviewPK implements Serializable {

    private String reviewer;
    private String reviewee;
}
