package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Stateless
public class UserDAO extends AbstractDAO<User> {
    @Getter @PersistenceContext (unitName = "mainDB")
    private EntityManager entityManager;
    public UserDAO() {
        super(User.class);
    }
    public List<User> findUsersMatchingName() {
    throw new UnsupportedOperationException("Not yet implemented");
    }
}
