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
import services.DatabaseService;

/**
 *
 * @author RED-HAWK
 */
public class CreateServer {

    private final DatabaseService dbService =  new DatabaseService();
    
    private boolean isDatabaseCreated() throws ClassNotFoundException, SQLException, FileNotFoundException {
        dbService.createDatabse(); // Create database if not present
        if (dbService.getDatabaseExistence() == false) {
            CreateServer cs = new CreateServer();
            return cs.createDatabse();
        }
        return false;
    }

    private boolean createDatabse() throws FileNotFoundException, ClassNotFoundException, SQLException {
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

    public void setUpEnv() throws ClassNotFoundException, SQLException, FileNotFoundException {
        isDatabaseCreated();
        
          // Functionality to delete database environment setup files
//        String abPath = new File("").getAbsolutePath();
//        File f1 = new File(abPath+"/src/java/servers/CreateServer.java");
//        File f2 = new File(abPath+"/src/java/testers/EnvironmentSetUpTester.java");
//        
//        if(f1.delete()){
//            f2.delete();
//        }
    }
}
