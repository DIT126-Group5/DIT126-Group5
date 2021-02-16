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
public class Author implements Serializable {

    @Id
    private String name;

    public Author(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "authors")
    private List<Book> book;
}
