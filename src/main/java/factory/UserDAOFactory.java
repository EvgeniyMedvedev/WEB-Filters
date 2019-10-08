package factory;

import DAO.UserDAO;
import DAO.UserDAOHibernateImpl;
import DAO.UserDaoJDBCImpl;

import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UserDAOFactory {
    private static FileInputStream file;
    private static Properties properties = new Properties();

    public static UserDAO getUserDAO(){
        try {
            file = new FileInputStream("D:/#include/TrainingMaterial/JavaMentor/" +
                        "PreProject/servlet-tutorial-lesson-01.get_started/One/src/main/resources/config.properties");
            properties.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String typeConnection = properties.getProperty("typeConnection");
        if (typeConnection.equalsIgnoreCase("hibernate")){
            return new UserDAOHibernateImpl();
        }else if (typeConnection.equalsIgnoreCase("jdbc")){
            return new UserDaoJDBCImpl();
        }
        return null;
    }
}
