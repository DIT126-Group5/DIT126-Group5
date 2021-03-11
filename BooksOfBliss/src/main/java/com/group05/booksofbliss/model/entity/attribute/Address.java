package com.group05.booksofbliss.model.entity.attribute;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {

    @NonNull
    @NotBlank(message = "Gatans namn får inte vara tomt")
    private String street;
    @NonNull
    @NotBlank(message = "Postnumret får inte vara tomt")
    private String postalCode;
    @NonNull
    @NotBlank(message = "Stadens namn får inte vara tomt")
    private String city;
}
