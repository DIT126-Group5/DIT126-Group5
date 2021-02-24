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
import lombok.Getter;

@Data
@Named
@ViewScoped
public class PublishListingBackingBean implements Serializable {
    
    @Inject
    private ListingDAO listingDAO;
    @Inject
    private ConditionDAO conditionDAO;
    //List<Condition> conditions = conditionDAO.findAll();
    private List<Condition> conditions;
    private double price;
    private String description;
    private Condition condition;
    
    @PostConstruct
    public void init() {
        conditions = new ArrayList();
        conditions = conditionDAO.findAll();
    }
}
