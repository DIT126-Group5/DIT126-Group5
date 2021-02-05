/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.booksofbliss.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable{
    @Id private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String address;
    private double balance = 0;
    
    public void setUsername(String username){
        if(!isValidUsername(username))
            throw new IllegalArgumentException("The username you have written is invalid.");
        this.username = username;
    }
    
    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("The password you have written is invalid.");
        }
        this.password = password;
    }
    
    /*Username Requirements:
    - Username must be between 5 and 30 characters long.
    - Username must contain atleast a lowercase letter from a-z.
    */
    public boolean isValidUsername(String username){
        if(!(5 <= username.length())
                || !(username.length() <= 30)
                || !username.matches(".*[a-z].*")){
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
                || !password.matches(".*[~!@#$%^*-_=+[{]}/;:,.?].*")
                || !password.matches(".*[0-9].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*[A-Z].*")){
            return false;
        }
        return true;
    }
}