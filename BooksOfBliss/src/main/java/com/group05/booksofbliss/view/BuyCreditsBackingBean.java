package com.group05.booksofbliss.view;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import lombok.Data;

@Data
@Named("buyCreditsBackingBean")
@ViewScoped
public class BuyCreditsBackingBean implements Serializable {

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Insättningsbeloppet måste vara en krona eller mer")
    private BigDecimal amount = BigDecimal.ZERO;
}
