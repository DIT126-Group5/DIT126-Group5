package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Book implements Serializable {
    @Id private String isbn;
    private String title;
    
//    @JoinTable(name = "book_author",
//            joinColumns = @JoinColumn(name = "book"),
//            inverseJoinColumns = @JoinColumn(name = "author")
//            )
//    @ManyToMany
//    private List<Author> authors;
    
@JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book"),
            inverseJoinColumns = @JoinColumn(name = "category")
            )
   @ManyToMany
    private List<Category> categories;
    

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}