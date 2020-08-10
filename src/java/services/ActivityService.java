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
import models.ActivityInfo;
import queries.ActivityQueryHandler;
import servers.DB;
import queries.QueryHandlerInterface;

/**
 *
 * @author RED-HAWK
 */
public class ActivityService implements ServiceInterface<ActivityInfo> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface activityQueryHandler = new ActivityQueryHandler();

    @Override
    public boolean add(ActivityInfo activityInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(activityQueryHandler.getAddDataQuery());
            ps.setString(1, activityInfo.getUserId());
            ps.setString(2, activityInfo.getAction());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(ActivityInfo activity) throws ClassNotFoundException, SQLException {
        return false; // Updating activity is not permitted
    }

    @Override
    public boolean remove(ActivityInfo activity) throws ClassNotFoundException, SQLException {
        return false; // Removing activity is not permitted
    }

    @Override
    public ActivityInfo get(ActivityInfo activityInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(activityQueryHandler.getFetchDataQuery());
            ps.setString(1, activityInfo.getUserId());
            rs = ps.executeQuery();

            ActivityInfo dbActivityInfo = new ActivityInfo();
            while (rs.next()) {
                dbActivityInfo.setId(rs.getInt(1));
                dbActivityInfo.setUserId(rs.getString(2));
                dbActivityInfo.setUserName(rs.getString(3));
                dbActivityInfo.setUserPhotoURL(rs.getString(4));
                dbActivityInfo.setAction(rs.getString(5));
                dbActivityInfo.setOccuredAt(rs.getTimestamp(6));
            }
            return activityInfo;
        }
        return null; //By default if connection to database fails, method will return null
    }

    @Override
    public List<ActivityInfo> getAll() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(activityQueryHandler.getFetchAllDataQuery());
            rs = ps.executeQuery();

            List<ActivityInfo> activityInfoList = new ArrayList<>();
            while (rs.next()) {
                ActivityInfo dbActivityInfo = new ActivityInfo();
                dbActivityInfo.setId(rs.getInt(1));
                dbActivityInfo.setUserId(rs.getString(2));
                dbActivityInfo.setUserName(rs.getString(3));
                dbActivityInfo.setUserPhotoURL(rs.getString(4));
                dbActivityInfo.setAction(rs.getString(5));
                dbActivityInfo.setOccuredAt(rs.getTimestamp(6));
                activityInfoList.add(dbActivityInfo);
            }
            return activityInfoList;
        }
        return null; //By default if connection to database fails, method will return null
    }

}
