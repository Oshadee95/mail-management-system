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
import java.util.ArrayList;
import java.util.List;
import models.Activity;
import queries.ActivityQueryHandler;
import servers.DB;
import queries.QueryHandlerInterface;

/**
 *
 * @author RED-HAWK
 */
public class ActivityService implements ServiceInterface<Activity> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface activityQueryHandler = new ActivityQueryHandler();

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
       if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(activityQueryHandler.getFetchDataQuery());
            ps.setString(1, activity.getUserId());
            rs = ps.executeQuery();

            Activity dbActivity = new Activity();
            while (rs.next()) {
                dbActivity.setId(rs.getInt(1));
                dbActivity.setAction(rs.getString(2));
                dbActivity.setOccuredAt(rs.getTimestamp(3));
            }
            return dbActivity;
        }
        return null; //By default if connection to database fails, method will return null
    }

    @Override
    public List<Activity> getAll() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(activityQueryHandler.getFetchAllDataQuery());
            rs = ps.executeQuery();

            List<Activity> activityList = new ArrayList<>();
            while (rs.next()) {
                Activity dbActivity = new Activity();
                dbActivity.setId(rs.getInt(1));
                dbActivity.setUserId(rs.getString(2)+"|"+rs.getString(3));
                dbActivity.setAction(rs.getString(4));
                dbActivity.setOccuredAt(rs.getTimestamp(5));
                activityList.add(dbActivity);
            }
            return activityList;
        }
        return null; //By default if connection to database fails, method will return null
    }

}
