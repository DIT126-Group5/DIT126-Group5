package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Condition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

@Data
@Named
@ViewScoped
public class PublishListingBackingBean implements Serializable {

    @Inject
    private ConditionDAO conditionDAO;
    
    private List<Condition> conditions;
    private double price;
    private String isbn;
    private String description;
    private String title;
    private List<String> authors;
    private String imageUrl;
    private List<String> categories;

    @PostConstruct
    public void init() {
        conditions = new ArrayList();
        conditions = conditionDAO.findAll();
    }
}
