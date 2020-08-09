/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import java.sql.SQLException;
import servers.DB;

/**
 *
 * @author RED-HAWK
 */
public class DatabaseQueryHandler {

    public String getTableExistence() throws ClassNotFoundException, SQLException {
        UserQueryHandler userQueryHandler = new UserQueryHandler();
        return "SELECT COUNT('TABLE_NAME') AS 'RESULT' FROM information_schema.tables WHERE table_schema = '" + DB.getDbName() + "' AND table_name = '" + userQueryHandler.getTableName() + "'";
    }
}
