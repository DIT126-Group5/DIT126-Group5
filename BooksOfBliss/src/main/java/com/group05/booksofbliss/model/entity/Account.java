package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.entity.attribute.Address;
import java.io.Serializable;
import java.util.List;
import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class Account implements Serializable {

    @Id
    @NonNull
    @NotNull
    @EqualsAndHashCode.Include
    @ToString.Include
    @Size(min = 5, message = "Användarnamnet måste innehålla minst 5 karaktärer")
    private String username;

    @NonNull
    @NotBlank(message = "Förnamn får inte vara tomt")
    @ToString.Include
    private String firstname;

    @NonNull
    @NotBlank(message = "Efternamn får inte vara tomt")
    @ToString.Include
    private String lastname;

    @NonNull
    @NotBlank(message = "Mobilnummer får inte vara tomt")
    @ToString.Include
    private String phonenumber;

    @NonNull
    @NotBlank(message = "E-post får inte vara tom")
    @ToString.Include
    @Email(message = "E-post är inte giltig")
    private String email;

    @NonNull
    @NotBlank
    @ToString.Include
    private String password;

    @NonNull
    @NotNull
    @ToString.Include
    @Embedded
    private Address address;

    @NonNull
    @NotNull
    @ToString.Include
    @Column(precision = 12, scale = 2)
    private MonetaryAmount balance;

    @OneToMany(mappedBy = "reviewer")
    private List<UserReview> reviewsGiven;

    @OneToMany(mappedBy = "reviewee")
    private List<UserReview> reviewsReceived;

    @OneToMany(mappedBy = "publishedBy")
    private List<Listing> listings;

    @OneToMany(mappedBy = "account")
    private List<Purchase> purchases;

    public void setUsername(String username) {
        if (username == null) {
            throw new NullPointerException("Username may not be null");
        }
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("The username you have written is invalid.");
        }
        this.username = username;
    }

    /*Username Requirements:
    - Username must be between 5 and 30 characters long.
    - Username must contain atleast a lowercase letter from a-z.
     */
    public boolean isValidUsername(String username) {
        if (!(5 <= username.length())
                || !(username.length() <= 30)
                || !username.matches(".*[a-z].*")) {
            return false;
        }
        return true;
    }

    /*Password Requirements:
    - Password must be atleast 10 characters long.
    - Password must be at most 100 characters long.
    - Password must contain atleast one of the special characters "~!@#$%^*-_=+[{]}/;:,.?"
    - Password must contain atleast one number.
    - Password must contain atleast an uppercase and a lowercase letter from A-Z.
     */
    public boolean isValidPassword(String password) {
        if (!(10 <= password.length())
                || !(password.length() <= 100)
                || !password.matches(".*[0-9].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*[A-Z].*")) {
            return false;
        }
        return true;
    }

    public void withdrawFromBalance(MonetaryAmount amount) {
        if (amount.isNegative()) {
            throw new IllegalArgumentException("Amount may not be negative");
        }
        MonetaryAmount newBalance = getBalance().subtract(amount);
        if (newBalance.isNegative()) {
            throw new IllegalStateException("Account balance is too low");
        }

        balance = newBalance;
    }

    public void addToBalance(MonetaryAmount amount) {
        if (amount.isNegative()) {
            throw new IllegalArgumentException("Amount may not be negative");
        }

        balance = balance.add(amount);
    }

    public double getReputation() {
        return reviewsReceived.stream()
                .mapToDouble(review -> review.getRating())
                .average()
                .orElse(0);
    }
}
