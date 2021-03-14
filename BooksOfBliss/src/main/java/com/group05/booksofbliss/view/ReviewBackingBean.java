package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.Param;

@Getter
@Setter
@Named("reviewBackingBean")
@ViewScoped
public class ReviewBackingBean implements Serializable {

    @Setter(AccessLevel.NONE)
    @Param(pathIndex = 0, converter = "#{listingConverter}")
    private Listing listing;

    @Min(value = 1, message = "Betyget måste vara mellan 1 och 5")
    @Max(value = 5, message = "Betyget måste vara mellan 1 och 5")
    private int rating = 5;

    @NotNull
    private String comment = "";
}
