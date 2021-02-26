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
        Account acc = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"),
                Money.of(10, "SEK")
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
