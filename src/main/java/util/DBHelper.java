package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBHelper {

    private static DBHelper helper;

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        if (helper == null) {
            helper = new DBHelper();
        }
        return helper;
    }

    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    @SuppressWarnings("UnusedDeclaration")
    private SessionFactory createSessionFactory() {
        Configuration configuration = getPostgresConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);

    }

    private Configuration getPostgresConfiguration() {
        Configuration configuration = new Configuration();
//        Properties properties = new Properties();
//        FileInputStream fil;
//        try {
//            fil = new FileInputStream("D:/#include/TrainingMaterial/JavaMentor/" +
//                    "PreProject/servlet-tutorial-lesson-01.get_started/One/src/main/resources/config.properties");
//            properties.load(fil);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        configuration.mergeProperties(properties);
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/db_example");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    public Connection getPostgresConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:postgresql://").   //db type
                    append("localhost:").           //host name
                    append("5432/").                //port
                    append("postgres");             //db name

            String login = "postgres";
            String pass = "root";

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString(), login, pass);
            return connection;
        } catch (SQLException | NoClassDefFoundError | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
