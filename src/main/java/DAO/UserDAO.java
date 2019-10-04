package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAll() ;

    void add(User t) ;

    User getById(long id) ;

    void delete(User t);

    void updateUser(User user, String name, String surName);
}
