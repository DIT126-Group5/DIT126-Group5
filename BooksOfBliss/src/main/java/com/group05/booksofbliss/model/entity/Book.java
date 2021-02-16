package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Book implements Serializable {

    @Id
    @NonNull
    private String isbn;
    @NonNull
    private String title;

    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book"),
            inverseJoinColumns = @JoinColumn(name = "author")
    )
    @ManyToMany //(cascade = {CascadeType.MERGE})
    @NonNull
    private List<Author> authors;

    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book"),
            inverseJoinColumns = @JoinColumn(name = "category")
    )
    @ManyToMany
    @NonNull
    private List<Category> categories;

    @OneToMany(mappedBy = "book")
    private List<Listing> listings;

}
