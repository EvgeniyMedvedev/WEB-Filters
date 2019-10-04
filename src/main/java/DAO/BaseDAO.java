package DAO;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

    List<T> getAll() ;

    void add(T t) ;

    T getById(long id) ;

    void delete(T t);
}
