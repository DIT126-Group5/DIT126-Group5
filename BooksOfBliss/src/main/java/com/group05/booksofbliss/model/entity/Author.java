/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Author implements Serializable {
    @Id private String name;
    
    public Author(String name) {
        this.name = name;
    }
    
   @JoinTable(name = "author_written_books",
            joinColumns = @JoinColumn(name = "author"),
            inverseJoinColumns = @JoinColumn(name = "book")
            )
   @ManyToMany
    private List<Category> book;
}