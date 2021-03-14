package com.group05.booksofbliss.model.database.dao.key;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReviewPK implements Serializable {

    private String reviewer;
    private String reviewee;
}
