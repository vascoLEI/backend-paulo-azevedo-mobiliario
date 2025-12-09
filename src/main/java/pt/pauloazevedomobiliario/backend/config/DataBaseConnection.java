package pt.pauloazevedomobiliario.backend.config;

import java.sql.*;

public class DataBaseConnection {
    static Connection connection = null;
    public static Connection getConnection() {
        try {
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);
            String host = System.getenv("DB_HOST");
            String db = System.getenv("DB_NAME");
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

            String url = "jdbc:postgresql://" + host + ":5432/" + db;

            if(connection==null){
                connection = DriverManager.getConnection(url, username, password);
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("This driver was not found.");
            return null;
        }
        catch (SQLException e) {
            System.out.println("It is impossible to connect with Data Base.");
            return null;
        }
        return connection;
    }
}