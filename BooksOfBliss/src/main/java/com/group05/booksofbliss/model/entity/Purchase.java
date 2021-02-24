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
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
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
    @EqualsAndHashCode.Include
    @ToString.Include
    @OneToOne(optional = false)
    @JoinColumn(name = "account")
    private Account account;

    @NonNull
    @NotNull
    @ToString.Include
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    //kan vara värt att uppgrade till en senare version av eclipselink för att
    //ha tillgång till LocalDateTime. v 2.7 och uppåt av eclipselink
    //kan skriva över i POM:en

    @NonNull
    @NotNull
    @ToString.Include
    @Embedded
    private Address deliveryAddress;

}
