package services;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

// Singleton design pattern implemented to avoid duplicate database instances 
public class DBConnection {

    private static DBConnection db;
    private static Connection connection;
    private final String url = "jdbc:mysql://localhost/";
    private final String dbname = "mmdb";
    private final String username = "root";
    private final String password = "";

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url + dbname, username, password);
    }

    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (db == null) {
            db = new DBConnection();
            return db;
        } else {
            return db;
        }
    }

    public static Connection getConnction() {
        return connection;
    }
}
