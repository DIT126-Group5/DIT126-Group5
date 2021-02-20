package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.database.dao.key.UserReviewPK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@IdClass(UserReviewPK.class)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserReview implements Serializable {

    @Id
    @NonNull
    @NotNull
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "reviewer")
    private Account reviewer;

    @Id
    @NonNull
    @NotNull
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "reviewee")
    private Account reviewee;

    @NonNull
    @NotNull
    private String comment;

    @NonNull
    @NotNull
    private int rating;

    //Kanske inte rätt sätt att modellera detta på
    //Gav förslag på ISA-relation
}
