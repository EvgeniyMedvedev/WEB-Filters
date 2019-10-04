package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public static Connection getPostgresqlConnection() {
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
        } catch (SQLException |NoClassDefFoundError|ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
