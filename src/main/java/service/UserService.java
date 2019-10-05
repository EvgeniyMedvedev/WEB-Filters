package service;

import DAO.UserDAO;
import DAO.UserDAOHibernateImpl;
import DAO.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private static UserDAO userDAO = new UserDaoJDBCImpl();
    private static UserDAOHibernateImpl userDAOHibernate = new UserDAOHibernateImpl();

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return userDAOHibernate.getAll();
    }

    public void addUser(User user) {
        userDAOHibernate.add(user);
    }

    public void deleteUser(User user) {
        userDAOHibernate.delete(user);
    }

    public boolean updateUser(User user,String name,String surName) {
        user.setFirstName(name);
        user.setLastName(surName);
        userDAOHibernate.updateUser(user, name,surName);
        return true;
    }

    public User getUserById(long id) {
        return userDAOHibernate.getById(id);
    }
}
