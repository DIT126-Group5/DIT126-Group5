package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Condition implements Serializable {
    @Id private String name;
    
    @OneToMany(mappedBy = "condition")
    private List<Listing> listings;

    public Condition(String name) {
        this.name = name;
    }
}
