package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.entity.Condition;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@Named
@ViewScoped
public class PublishListingBackingBean implements Serializable {

    @Inject
    private ConditionDAO conditionDAO;
    private final String reg = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|"
            + "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
    private List<String> conditions;
    private BigDecimal price;
    @Pattern(regexp = reg, message = "Felaktigt ISBN.")
    private String isbn;
    private String description;
    private String title;
    private List<String> authors;
    private String imageUrl = "";
    private List<String> categories = null;
    private int publishDate;
    private String conditionName;
    private boolean showPublishForm = false;

    @PostConstruct
    public void init() {
        conditions = new ArrayList();
        conditionDAO.findAll().forEach(c -> {
            conditions.add(c.getName());
        });
    }
}
