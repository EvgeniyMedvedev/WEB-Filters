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
        session = DBHelper.getSessionFactory().openSession();
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
        for (User user : getAll()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete User where id =: idUser");
            query.setParameter("idUser", user.getId()).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user, String name, String surname) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("update User set name =:userName,surname =:userSurname where id =: idUser");
            query.setParameter("userName", name);
            query.setParameter("userSurname", surname);
            query.setParameter("idUser", user.getId()).executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
