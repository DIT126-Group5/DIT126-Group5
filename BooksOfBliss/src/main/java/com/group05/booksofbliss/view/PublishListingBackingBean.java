package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Condition;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import lombok.Getter;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

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
    private Condition condition;
    private String isbn;

    @PostConstruct
    public void init() {
        conditions = new ArrayList();
        conditions = conditionDAO.findAll();
    }


}
