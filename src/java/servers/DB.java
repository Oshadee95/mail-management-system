package servers;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import configurations.DBConfig;

// Singleton design pattern implemented to avoid duplicate database instances 
public class DB {

    private static DB db;
    private static Connection connection;
    private final DBConfig dbConfig = new DBConfig();

    private DB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbConfig.getUrl() + dbConfig.getDbName(), dbConfig.getUsername(), dbConfig.getPassword());
    }

    public static DB getInstance() throws ClassNotFoundException, SQLException {
        if (db == null) {
            return db = new DB();
        } else {
            return db;
        }
    }

    public static Connection getConnction() {
        return connection;
    }
}
