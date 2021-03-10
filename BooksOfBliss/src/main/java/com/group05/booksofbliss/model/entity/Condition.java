package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Condition implements Serializable {

    @Id
    @NonNull
    @NotBlank(message = "Skickets namn får inte vara tomt")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    // Köpare kanske ska ha condition också, i purchase
    @OneToMany(mappedBy = "condition")
    private List<Listing> listings;
}
