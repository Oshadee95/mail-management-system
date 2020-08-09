/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import services.DatabaseServices;

/**
 *
 * @author RED-HAWK
 */
public class CreateServer {

    private boolean isDatabaseCreated() throws ClassNotFoundException, SQLException, FileNotFoundException {
        if (new DatabaseServices().getDatabaseExistence() == false) {
            CreateServer cs = new CreateServer();
            return cs.createDatabse();
        }
        return false;
    }

    private boolean createDatabse() throws FileNotFoundException {
        if (DB.getConnction() != null) {
            Connection con = DB.getConnction();
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/src/java/servers/script.sql"));
            //Running the script
            sr.runScript(reader);
            return true;
        }
        return false;
    }
    
    public boolean setUpEnv() throws ClassNotFoundException, SQLException, FileNotFoundException {
        return isDatabaseCreated();
    }
}
