package service;

import DAO.UserDAO;
import DAO.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private static UserDAO userDAO = new UserDaoJDBCImpl();

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
        return userDAO.getById(id);
    }


}
