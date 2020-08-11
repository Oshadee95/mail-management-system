/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import java.sql.SQLException;
import models.Database;

/**
 *
 * @author RED-HAWK
 */
public class DatabaseQueryHandler {
    private final Database dbConfig = new Database();
    
    public String getTableExistenceQuery() throws ClassNotFoundException, SQLException {
        UserQueryHandler userQueryHandler = new UserQueryHandler();
        return "SELECT COUNT('TABLE_NAME') AS 'RESULT' FROM information_schema.tables WHERE table_schema = '" + dbConfig.getDbName() + "' AND table_name = '" + userQueryHandler.getTableName() + "'";
    }
    
    public String getDatabaseCreateQuery(){
        return "CREATE DATABASE IF NOT EXISTS "+dbConfig.getDbName()+"";
    }   
}
