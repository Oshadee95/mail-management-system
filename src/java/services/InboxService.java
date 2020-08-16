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
import models.InboxInfo;
import models.UserInfo;
import queries.InboxQueryHandler;
import servers.DB;

/**
 *
 * @author Oshadee
 */
public class InboxService implements ServiceInterface<InboxInfo> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    InboxQueryHandler inboxQueryHandler = new InboxQueryHandler();

    @Override
    public boolean add(InboxInfo inboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getAddDataQuery());
            ps.setString(1, inboxInfo.getId());
            ps.setString(2, inboxInfo.getType());
            ps.setInt(3, inboxInfo.getCategoryId());
            ps.setString(4, inboxInfo.getSender());
            ps.setString(5, inboxInfo.getContent());
            ps.setString(6, inboxInfo.getCollectorId());
            ps.setString(7, inboxInfo.getRecipientId());
            ps.setString(8, inboxInfo.getImageURL());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(InboxInfo inboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getUpdateDataQuery());
            ps.setString(1, inboxInfo.getType());
            ps.setInt(2, inboxInfo.getCategoryId());
            ps.setString(3, inboxInfo.getSender());
            ps.setString(4, inboxInfo.getContent());
            ps.setString(5, inboxInfo.getRecipientId());
            ps.setString(6, inboxInfo.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }
    
    public boolean updateStatus(InboxInfo inboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getUpdateStatusQuery());
            ps.setString(1, inboxInfo.getReplied());
            ps.setString(2, inboxInfo.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean remove(InboxInfo inboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getRemoveDataQuery());
            ps.setString(1, inboxInfo.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public InboxInfo get(InboxInfo inboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getFetchDataQuery());
            ps.setString(1, inboxInfo.getId());
            rs = ps.executeQuery();

            InboxInfo dbInboxInfo = new InboxInfo();
            while (rs.next()) {
                dbInboxInfo.setId(rs.getString(1));
                dbInboxInfo.setType(rs.getString(2));
                dbInboxInfo.setCategoryId(rs.getInt(3));
                dbInboxInfo.setCategoryName(rs.getString(4));
                dbInboxInfo.setSender(rs.getString(5));
                dbInboxInfo.setContent(rs.getString(6));
                dbInboxInfo.setCollectorId(rs.getString(7));
                dbInboxInfo.setCollectorName(rs.getString(8));
                dbInboxInfo.setCollectorPhotoURL(rs.getString(9));
                dbInboxInfo.setRecipientId(rs.getString(10));
                dbInboxInfo.setRecipientName(rs.getString(11));
                dbInboxInfo.setRecipientPhotoURL(rs.getString(12));
                dbInboxInfo.setImageURL(rs.getString(13));
                dbInboxInfo.setReplied(rs.getString(14));
                dbInboxInfo.setRecordedAt(rs.getTimestamp(15));
                dbInboxInfo.setUpdatedAt(rs.getTimestamp(16));
                dbInboxInfo.setAllocatedOffice(rs.getString(17));
            }
            return dbInboxInfo;
        }
        return null; //By default if connection to database fails, method will return null
    }

    @Override
    public List<InboxInfo> getAll() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getFetchAllDataQuery());
            rs = ps.executeQuery();

            List<InboxInfo> inboxInfoList = new ArrayList<>();
            while (rs.next()) {
                InboxInfo dbInboxInfo = new InboxInfo();
                dbInboxInfo.setId(rs.getString(1));
                dbInboxInfo.setType(rs.getString(2));
                dbInboxInfo.setCategoryId(rs.getInt(3));
                dbInboxInfo.setCategoryName(rs.getString(4));
                dbInboxInfo.setSender(rs.getString(5));
                dbInboxInfo.setContent(rs.getString(6));
                dbInboxInfo.setCollectorId(rs.getString(7));
                dbInboxInfo.setCollectorName(rs.getString(8));
                dbInboxInfo.setCollectorPhotoURL(rs.getString(9));
                dbInboxInfo.setRecipientId(rs.getString(10));
                dbInboxInfo.setRecipientName(rs.getString(11));
                dbInboxInfo.setRecipientPhotoURL(rs.getString(12));
                dbInboxInfo.setImageURL(rs.getString(13));
                dbInboxInfo.setReplied(rs.getString(14));
                dbInboxInfo.setRecordedAt(rs.getTimestamp(15));
                dbInboxInfo.setUpdatedAt(rs.getTimestamp(16));
                dbInboxInfo.setAllocatedOffice(rs.getString(17));
                inboxInfoList.add(dbInboxInfo);
            }
            return inboxInfoList;
        }
        return null; //By default if connection to database fails, method will return null    
    }
    
    public List<InboxInfo> getAllByUser(UserInfo user) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getFetchAllDataByUser());
            ps.setString(1, user.getId());
            rs = ps.executeQuery();

            List<InboxInfo> inboxInfoList = new ArrayList<>();
            while (rs.next()) {
                InboxInfo dbInboxInfo = new InboxInfo();
                dbInboxInfo.setId(rs.getString(1));
                dbInboxInfo.setType(rs.getString(2));
                dbInboxInfo.setCategoryId(rs.getInt(3));
                dbInboxInfo.setCategoryName(rs.getString(4));
                dbInboxInfo.setSender(rs.getString(5));
                dbInboxInfo.setContent(rs.getString(6));
                dbInboxInfo.setCollectorId(rs.getString(7));
                dbInboxInfo.setCollectorName(rs.getString(8));
                dbInboxInfo.setCollectorPhotoURL(rs.getString(9));
                dbInboxInfo.setRecipientId(rs.getString(10));
                dbInboxInfo.setRecipientName(rs.getString(11));
                dbInboxInfo.setRecipientPhotoURL(rs.getString(12));
                dbInboxInfo.setImageURL(rs.getString(13));
                dbInboxInfo.setReplied(rs.getString(14));
                dbInboxInfo.setRecordedAt(rs.getTimestamp(15));
                dbInboxInfo.setUpdatedAt(rs.getTimestamp(16));
                dbInboxInfo.setAllocatedOffice(rs.getString(17));
                inboxInfoList.add(dbInboxInfo);
            }
            return inboxInfoList;
        }
        return null; //By default if connection to database fails, method will return null    
    }
    
    public List<InboxInfo> getAllByOffice(UserInfo user) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getFetchAllDataByOffice());
            ps.setString(1, user.getOffice());
            rs = ps.executeQuery();

            List<InboxInfo> inboxInfoList = new ArrayList<>();
            while (rs.next()) {
                InboxInfo dbInboxInfo = new InboxInfo();
                dbInboxInfo.setId(rs.getString(1));
                dbInboxInfo.setType(rs.getString(2));
                dbInboxInfo.setCategoryId(rs.getInt(3));
                dbInboxInfo.setCategoryName(rs.getString(4));
                dbInboxInfo.setSender(rs.getString(5));
                dbInboxInfo.setContent(rs.getString(6));
                dbInboxInfo.setCollectorId(rs.getString(7));
                dbInboxInfo.setCollectorName(rs.getString(8));
                dbInboxInfo.setCollectorPhotoURL(rs.getString(9));
                dbInboxInfo.setRecipientId(rs.getString(10));
                dbInboxInfo.setRecipientName(rs.getString(11));
                dbInboxInfo.setRecipientPhotoURL(rs.getString(12));
                dbInboxInfo.setImageURL(rs.getString(13));
                dbInboxInfo.setReplied(rs.getString(14));
                dbInboxInfo.setRecordedAt(rs.getTimestamp(15));
                dbInboxInfo.setUpdatedAt(rs.getTimestamp(16));
                dbInboxInfo.setAllocatedOffice(rs.getString(17));
                inboxInfoList.add(dbInboxInfo);
            }
            return inboxInfoList;
        }
        return null; //By default if connection to database fails, method will return null    
    }
}
