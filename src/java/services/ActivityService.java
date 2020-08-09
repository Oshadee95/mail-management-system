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
import java.util.List;
import models.Activity;
import queries.ActivityQueryHandler;
import queries.QueryHandler;
import servers.DB;

/**
 *
 * @author RED-HAWK
 */
public class ActivityService implements ServiceInterface<Activity> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandler activityQueryHandler = new ActivityQueryHandler();
    
    @Override
    public boolean add(Activity activity) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(activityQueryHandler.getAddDataQuery());
            ps.setString(1, activity.getUserId());
            ps.setString(2, activity.getAction());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Activity activity) throws ClassNotFoundException, SQLException {
        return false; // Updating activity is not permitted
    }

    @Override
    public boolean remove(Activity activity) throws ClassNotFoundException, SQLException {
        return false; // Removing activity is not permitted
    }

    @Override
    public Activity get(Activity activity) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
