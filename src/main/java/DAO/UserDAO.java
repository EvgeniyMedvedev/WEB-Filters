package DAO;

import model.User;
import util.DBHelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements BaseDAO<User> {
    private Connection connection;

    public UserDAO(){
        this.connection = DBHelper.getPostgresqlConnection();
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from db_example.users");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                long id = result.getLong("id");
                String firstName = result.getString("name");
                String lastName = result.getString("surname");
                list.add(new User(id,firstName,lastName));
            }
            result.close();
            statement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Long getLastId(){
        long id = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from db_example.users");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into db_example.users values (?,?,?)");
            long id = getLastId() + 1;
            user.setId(id);
            statement.setLong(1,id);
            statement.setString(2,user.getFirstName());
            statement.setString(3,user.getLastName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        for (User user:getAll()){
            if (id == user.getId()){
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from db_example.users where id=?");
            statement.setLong(1,user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user,String firstName,String lastName){
        try {
            PreparedStatement statement = connection.prepareStatement("update db_example.users set name=?,surname=? where id=?");
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setLong(3,user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //я изменение гы
    public void dropTable() {
        try {
            PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS users");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

