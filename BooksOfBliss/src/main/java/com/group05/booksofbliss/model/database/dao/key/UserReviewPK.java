package com.group05.booksofbliss.model.database.dao.key;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor
public final class UserReviewPK implements Serializable {

    @NonNull
    @NotNull
    private final String reviewer;

    @NonNull
    @NotNull
    private final String reviewee;
}
