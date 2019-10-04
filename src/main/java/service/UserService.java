package service;

import DAO.UserDAO;
import model.User;
import org.postgresql.Driver;
import util.DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;
    private static UserDAO userDAO = getUserDAO();

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public void addUser(User user) {
        userDAO.add(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    public boolean updateUser(User user,String name,String surName) {
        user.setFirstName(name);
        user.setLastName(surName);
        userDAO.updateUser(user, name,surName);
        return true;
    }

    public User getUserById(long id) {
        return getUserDAO().getById(id);
    }

    private static UserDAO getUserDAO() {
        return new UserDAO();
    }


}
