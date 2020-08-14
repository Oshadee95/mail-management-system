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
import models.UserInfo;
import queries.UserQueryHandler;
import servers.DB;

/**
 *
 * @author Oshadee
 */
public class UserService implements ServiceInterface<UserInfo> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    UserQueryHandler userQueryHandler = new UserQueryHandler();

    @Override
    public boolean add(UserInfo userInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getAddDataQuery());
            ps.setString(1, userInfo.getId());
            ps.setString(2, userInfo.getNic());
            ps.setString(3, userInfo.getFullName());
            ps.setString(4, userInfo.getDisplayName());
            ps.setInt(5, userInfo.getOccupationId());
            ps.setString(6, userInfo.getOffice());
            ps.setString(7, userInfo.getRoleId());
            ps.setString(8, userInfo.getActive());
            ps.setString(9, userInfo.getPassword());
            ps.setString(10, userInfo.getPhotoURL());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(UserInfo userInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getUpdateDataQuery());
            ps.setString(1, userInfo.getFullName());
            ps.setString(2, userInfo.getDisplayName());
            ps.setInt(3, userInfo.getOccupationId());
            ps.setString(4, userInfo.getOffice());
            ps.setString(5, userInfo.getRoleId());
            ps.setString(6, userInfo.getActive());
            ps.setString(7, userInfo.getPassword());
            ps.setString(8, userInfo.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean remove(UserInfo userInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getRemoveDataQuery());
            ps.setString(1, userInfo.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public UserInfo get(UserInfo userInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getFetchDataQuery());
            ps.setString(1, userInfo.getId());
            rs = ps.executeQuery();

            UserInfo dbUserInfo = new UserInfo();
            while (rs.next()) {
                dbUserInfo.setId(rs.getString(1));
                dbUserInfo.setNic(rs.getString(2));
                dbUserInfo.setFullName(rs.getString(3));
                dbUserInfo.setDisplayName(rs.getString(4));
                dbUserInfo.setOccupationId(rs.getInt(5));
                dbUserInfo.setOccupation(rs.getString(6));
                dbUserInfo.setOffice(rs.getString(7));
                dbUserInfo.setRoleId(rs.getString(8));
                dbUserInfo.setRoleWeight(rs.getInt(9));
                dbUserInfo.setActive(rs.getString(10));
                dbUserInfo.setPassword(rs.getString(11));
                dbUserInfo.setPhotoURL(rs.getString(12));
                dbUserInfo.setCreatedAt(rs.getTimestamp(13));
                dbUserInfo.setUpdatedAt(rs.getTimestamp(14));
            }
            return dbUserInfo;
        }
        return null; //By default if connection to database fails, method will return null
    }

    @Override
    public List<UserInfo> getAll() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getFetchAllDataQuery());
            rs = ps.executeQuery();

            List<UserInfo> userInfoList = new ArrayList<>();
            while (rs.next()) {
                UserInfo dbUserInfo = new UserInfo();
                dbUserInfo.setId(rs.getString(1));
                dbUserInfo.setNic(rs.getString(2));
                dbUserInfo.setFullName(rs.getString(3));
                dbUserInfo.setDisplayName(rs.getString(4));
                dbUserInfo.setOccupationId(rs.getInt(5));
                dbUserInfo.setOccupation(rs.getString(6));
                dbUserInfo.setOffice(rs.getString(7));
                dbUserInfo.setRoleId(rs.getString(8));
                dbUserInfo.setRoleWeight(rs.getInt(9));
                dbUserInfo.setActive(rs.getString(10));
                dbUserInfo.setPhotoURL(rs.getString(12));
                dbUserInfo.setCreatedAt(rs.getTimestamp(13));
                dbUserInfo.setUpdatedAt(rs.getTimestamp(14));
                userInfoList.add(dbUserInfo);
            }
            return userInfoList;
        }
        return null; //By default if connection to database fails, method will return null
    }

    public List<UserInfo> getAllOnLowLevel() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getFetchAllLowLevelDataQuery());
            rs = ps.executeQuery();

            List<UserInfo> userInfoList = new ArrayList<>();
            while (rs.next()) {
                UserInfo dbUserInfo = new UserInfo();
                dbUserInfo.setId(rs.getString(1));
                dbUserInfo.setNic(rs.getString(2));
                dbUserInfo.setFullName(rs.getString(3));
                dbUserInfo.setDisplayName(rs.getString(4));
                dbUserInfo.setOccupationId(rs.getInt(5));
                dbUserInfo.setOccupation(rs.getString(6));
                dbUserInfo.setOffice(rs.getString(7));
                dbUserInfo.setRoleId(rs.getString(8));
                dbUserInfo.setRoleWeight(rs.getInt(9));
                dbUserInfo.setActive(rs.getString(10));
                dbUserInfo.setPhotoURL(rs.getString(12));
                dbUserInfo.setCreatedAt(rs.getTimestamp(13));
                dbUserInfo.setUpdatedAt(rs.getTimestamp(14));
                userInfoList.add(dbUserInfo);
            }
            return userInfoList;
        }
        return null; //By default if connection to database fails, method will return null
    }
}
