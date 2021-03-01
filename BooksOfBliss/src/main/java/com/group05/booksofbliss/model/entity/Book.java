package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Book implements Serializable {

    @Id
    @NonNull
    @NotNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private String isbn;

    @NonNull
    @NotNull
    @ToString.Include
    private String title;

    @NonNull
    @NotNull
    @ToString.Include
    private int publicationYear;

    @NonNull
    @NotNull
    @ToString.Include
    private String imageUrl;

    @ToString.Include
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book"),
            inverseJoinColumns = @JoinColumn(name = "author")
    )
    @ManyToMany //(cascade = {CascadeType.MERGE})
    private List<Author> authors;

    @ToString.Include
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book"),
            inverseJoinColumns = @JoinColumn(name = "category")
    )
    @ManyToMany
    private List<Category> categories;

    @OneToMany(mappedBy = "book")
    private List<Listing> listings;
}
