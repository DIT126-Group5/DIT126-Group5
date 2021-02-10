package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Purchase implements Serializable {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "listing")
    private Listing listing;

    @OneToOne(optional = false)
    @JoinColumn(name = "account")
    private Account account;

    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    //kan vara värt att uppgrade till en senare version av eclipselink för att 
    //h atillgång till LocalDateTime. v 2.7 och uppåt av eclipselink
    //kan skriva över i POM:en
}
