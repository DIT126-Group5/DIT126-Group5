package com.group05.booksofbliss.model.database.dao.key;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewPK implements Serializable {

    @NonNull
    @NotNull
    private String reviewer;

    @NonNull
    @NotNull
    private String reviewee;
}
