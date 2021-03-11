package com.group05.booksofbliss.view;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("buyCreditsBackingBean")
@ViewScoped
public class BuyCreditsBackingBean implements Serializable {

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Insättningsbeloppet måste vara minst en krona")
    private BigDecimal amount = BigDecimal.ZERO;
}
