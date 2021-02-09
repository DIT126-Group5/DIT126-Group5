package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Listing implements Serializable {
    @Id 
    @GeneratedValue
    private int id;
    private Instant date;
    private double price;
    private String description;
    
    @JoinColumn(name = "condition")
    @ManyToOne
    private Condition condition;

    public Listing(int id, Instant date, double price, String description) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.description = description;
    }
}
