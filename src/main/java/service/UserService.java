package service;

import DAO.UserDAO;
import DAO.UserDAOHibernateImpl;
import DAO.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private static UserDAO userDAO = new UserDaoJDBCImpl();
    private static UserDAO userDAOHibernate = new UserDAOHibernateImpl();

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

    public void deleteUser(long id) {
        userDAOHibernate.delete(id);
    }

    public boolean updateUser(long idUser,String name,String surName) {
        userDAOHibernate.updateUser(idUser, name,surName);
        return true;
    }

    public boolean validId(int id){
        try {
            if (getUserById(id) != null){
                return true;
            }else {
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }

    }

    public User getUserById(long id) {
        return userDAOHibernate.getById(id);
    }
}
