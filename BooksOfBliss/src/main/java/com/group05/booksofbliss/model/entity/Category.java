package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Category implements Serializable {
    @Id private String name;
    

    @ManyToMany (mappedBy = "categories") List<Book> books;
    

    public Category(String name) {
        this.name = name;
    }
}