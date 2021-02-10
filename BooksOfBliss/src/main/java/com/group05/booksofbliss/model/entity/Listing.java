package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
public class Listing implements Serializable {

    @Id
    @GeneratedValue
    @NonNull
    private int id;
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @NonNull
    private double price;
    private String description;

    @JoinColumn(name = "condition")
    @ManyToOne
    private Condition condition;

    @JoinColumn(name = "publishedBy")
    @ManyToOne
    private Account publishedBy;

    @JoinColumn(name = "book")
    @ManyToOne
    private Book book;

    @OneToOne(mappedBy = "listing")
    private Purchase purchase;

    /*public Listing(int id, Date date, double price, String description) {
        this.id = id;
        this.dateTime = date;
        this.price = price;
        this.description = description;
    }*/
}
