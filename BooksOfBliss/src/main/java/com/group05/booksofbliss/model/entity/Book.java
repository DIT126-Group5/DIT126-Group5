package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.  AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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