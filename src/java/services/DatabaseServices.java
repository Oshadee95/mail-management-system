/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import queries.DatabaseQueryHandler;
import servers.DB;

/**
 *
 * @author RED-HAWK
 */
public class DatabaseServices {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult = 0; // execution result will either return 1 for successful execution and 0 for error
    DatabaseQueryHandler databaseQueryHandler = new DatabaseQueryHandler();

    public boolean getDatabaseExistence() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(databaseQueryHandler.getTableExistence());
            rs = ps.executeQuery();
            while (rs.next()) {
                eResult = rs.getInt(1);
            }
            return (eResult == 1);
        }
        return false;
    }
}
