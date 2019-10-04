package service;

import DAO.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private static UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return userDaoJDBCImpl.getAll();
    }

    public void addUser(User user) {
        userDaoJDBCImpl.add(user);
    }

    public void deleteUser(User user) {
        userDaoJDBCImpl.delete(user);
    }

    public boolean updateUser(User user,String name,String surName) {
        user.setFirstName(name);
        user.setLastName(surName);
        userDaoJDBCImpl.updateUser(user, name,surName);
        return true;
    }

    public User getUserById(long id) {
        return userDaoJDBCImpl.getById(id);
    }


}
