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
    @NotBlank
    private String street;
    @NonNull
    @NotBlank
    private String postalCode;
    @NonNull
    @NotBlank
    private String city;
}
