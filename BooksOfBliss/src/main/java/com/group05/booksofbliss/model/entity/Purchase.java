package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.entity.attribute.Address;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Purchase implements Serializable {

    @Id
    @NonNull
    @NotNull
    @EqualsAndHashCode.Include
    @OneToOne(optional = false)
    @JoinColumn(name = "listing")
    private Listing listing;

    @NonNull
    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "account")
    private Account account;

    @NonNull
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @NonNull
    @NotNull
    @Embedded
    private Address deliveryAddress;

}
