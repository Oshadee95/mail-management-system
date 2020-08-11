/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Database;
import queries.DatabaseQueryHandler;
import servers.DB;

/**
 *
 * @author RED-HAWK
 */
public class DatabaseService {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult = 0; // execution result will either return 1 for successful execution and 0 for error
    DatabaseQueryHandler databaseQueryHandler = new DatabaseQueryHandler();
    private final Database dbConfig = new Database();

    public boolean getDatabaseExistence() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(databaseQueryHandler.getTableExistenceQuery());
            rs = ps.executeQuery();
            while (rs.next()) {
                eResult = rs.getInt(1);
            }
            return (eResult == 1);
        }
        return false;
    }

    public boolean createDatabse() throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://localhost/";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, dbConfig.getUsername(), dbConfig.getPassword());
        ps = connection.prepareStatement(databaseQueryHandler.getDatabaseCreateQuery());
        eResult = ps.executeUpdate();
        return (eResult == 1); //This will return true if eResult is 1 and false if 0
    }
}
