package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {

    private Session session;

    public UserDAOHibernateImpl() {
        session = DBHelper.getInstance().getSessionFactory().openSession();
    }

    @Override
    public List<User> getAll() {
        List<User> list = session.createQuery("from User").list();
        return list;
    }

    @Override
    public void add(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        Query query = session.createQuery("from User where id =: idUser");
        return (User) query.setParameter("idUser",id).uniqueResult();
    }

    @Override
    public void delete(long id) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete User where id =: idUser");
            query.setParameter("idUser", id).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(long id, String name, String surname) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update User set name =:userName,surname =:userSurname where id =: idUser");
            query.setParameter("userName", name);
            query.setParameter("userSurname", surname);
            query.setParameter("idUser", id).executeUpdate();
            transaction.commit();
            getById(id).setFirstName(name);
            getById(id).setLastName(surname);
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String login, String password) {
        Query query = session.createQuery("from User where login =: loginUser");
        Query query1 = session.createQuery("from User where password =: passwordUser");
        User u = (User) query.setParameter("loginUser",login).uniqueResult();
        User r = (User) query1.setParameter("passwordUser",password).uniqueResult();
        if (u.equals(r)){
            return true;
        }
        return false;
//        for (User user:getAll()){
//            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public String getRole(String login, String password) {
        Query query = session.createQuery("from User where login =: loginUser");
        Query query1 = session.createQuery("from User where password =: passwordUser");
        User u = (User) query.setParameter("loginUser",login).uniqueResult();
        User r = (User) query1.setParameter("passwordUser",password).uniqueResult();
        String role = null;
        if (u.equals(r)){
            role = u.getRole();
        }
        return role;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Query query = session.createQuery("from User where login =: loginUser");
        Query query1 = session.createQuery("from User where password =: passwordUser");
        User u = (User) query.setParameter("loginUser",login).uniqueResult();
        User r = (User) query1.setParameter("passwordUser",password).uniqueResult();
        if (u.equals(r)){
            return u;
        }
        return null;

    }
}
