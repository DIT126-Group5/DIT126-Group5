package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.attribute.Address;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import org.javamoney.moneta.Money;

@Named
@ViewScoped
@Getter
public class ListingBackingBean implements Serializable {

    private List<Listing> listings;

    //@Inject
    //private ProductService service;
    @PostConstruct
    public void init() {
        Account acc = new Account("username", "firstname", "lastname", "password", new Address("Street", "45163", "City"), Money
                .of(50, "SEK")
        );
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Mr Bean");
        authors.add(author);

        /*listings.add(new Listing(
                new Date(), 0,
                "description",
                new Condition("kinda ok"),
                acc,
                (new Book("isbn1", "title", authors, Arrays.asList(new Category("Maths"))))));

        listings.add(new Listing(
                new Date(), 0,
                "description",
                new Condition("blegh"),
                acc,
                (new Book("isbn2", "loho", authors, Arrays.asList(new Category("not Maths"))))));

        listings.add(new Listing(
                new Date(), 0,
                "description",
                new Condition("ok"),
                acc,
                (new Book("isbn3", "hoho", authors, Arrays.asList(new Category("Deckkk"))))));*/
    }

    public List<Listing> getListings() {
        return this.listings;
    }
}
