package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    private Instant date;
}
