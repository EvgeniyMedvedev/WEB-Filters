package service;

import DAO.UserDAO;
import DAO.UserDAOHibernateImpl;
import DAO.UserDaoJDBCImpl;
import factory.UserDAOFactory;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;

    private static UserDAO userDAO = UserDAOFactory.getUserDAO();

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

    public User getUserById(long id) {
        return userDAO.getById(id);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userDAO.getUserByLoginAndPassword(login,password);
    }

    public String getRole(String login, String password){
        return userDAO.getRole(login,password);
    }

    public void addUser(User user) {
        userDAO.add(user);
    }

    public void deleteUser(long id) {
        userDAO.delete(id);
    }

    public boolean updateUser(long idUser,String name,String surName) {
        userDAO.updateUser(idUser, name,surName);
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

    public boolean validateUser(String login,String password){
        return userDAO.validateUser(login,password);
    }

}
