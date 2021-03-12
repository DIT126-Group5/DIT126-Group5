package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Listing implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @NonNull
    @NotNull
    @Column(precision = 12, scale = 2)
    private MonetaryAmount price;

    @NonNull
    @NotNull
    private String description;

    @NonNull
    @NotNull
    @JoinColumn(name = "condition")
    @ManyToOne //(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Condition condition;

    @NonNull
    @NotNull
    @JoinColumn(name = "publishedBy")
    @ManyToOne
    private Account publishedBy;

    @NonNull
    @NotNull
    @JoinColumn(name = "book")
    @ManyToOne
    private Book book;

    @ToString.Exclude
    @OneToOne(mappedBy = "listing")
    private Purchase purchase;

    public void setPrice(MonetaryAmount price) {
        if (price.isNegative()) {
            throw new IllegalArgumentException("The price must be 0 or higher.");
        } else {
            this.price = price;
        }
    }

    // Special handling of hashCode and equals for entity with generated primary key.
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Listing)) {
            return false;
        }
        Listing other = (Listing) obj;
        return getId() != null && getId().equals(other.getId());
    }

}
