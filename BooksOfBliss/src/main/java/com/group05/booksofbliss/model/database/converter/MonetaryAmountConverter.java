package com.group05.booksofbliss.model.database.converter;

import java.math.BigDecimal;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.javamoney.moneta.Money;

@Converter(autoApply = true)
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {

    // Hard coded since other currencies are out of scope for the project
    private static final CurrencyUnit SEK = Monetary.getCurrency("SEK");

    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount attribute) {
        if (attribute == null) {
            return null;
        }
        return Money.from(attribute).getNumberStripped();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal dbData) {
        if (dbData == null) {
            return null;
        }
        return Money.of(dbData, SEK);
    }
}
