package service;

import DAO.UserDAO;
import model.User;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return getUserDAO().getAllUsers();
    }

    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }

    public boolean updateUser(User user,String name,String surName) {
        user.setFirstName(name);
        user.setLastName(surName);
        getUserDAO().updateUser(user, name,surName);
        return true;
    }

    public User getUserById(long id) {
        return getUserDAO().getUserById(id);
    }

    private static Connection getPostgresqlConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:postgresql://").   //db type
                    append("localhost:").           //host name
                    append("5432/").                //port
                    append("postgres");           //db name

            String login = "postgres";
            String pass = "root";

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString(), login, pass);
            return connection;
        } catch (SQLException|NoClassDefFoundError|ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getPostgresqlConnection());
    }


}
