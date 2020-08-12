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
import models.OutboxInfo;
import queries.OutboxQueryHandler;
import servers.DB;
import queries.QueryHandlerInterface;

/**
 *
 * @author Oshadee
 */
public class OutboxService implements ServiceInterface<OutboxInfo> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface outboxQueryHandler = new OutboxQueryHandler();

    @Override
    public boolean add(OutboxInfo outboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getAddDataQuery());
            ps.setString(1, outboxInfo.getMailId());
            ps.setString(2, outboxInfo.getSenderId());
            ps.setString(3, outboxInfo.getContent());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(OutboxInfo outboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getUpdateDataQuery());
            ps.setString(1, outboxInfo.getContent());
            ps.setString(2, outboxInfo.getMailId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean remove(OutboxInfo outboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getRemoveDataQuery());
            ps.setString(1, outboxInfo.getMailId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public OutboxInfo get(OutboxInfo outboxInfo) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getFetchDataQuery());
            ps.setString(1, outboxInfo.getMailId());
            rs = ps.executeQuery();

            OutboxInfo dbOutboxInfo = new OutboxInfo();
            while (rs.next()) {
                dbOutboxInfo.setMailId(rs.getString(1));
                dbOutboxInfo.setType(rs.getString(2));
                dbOutboxInfo.setImageURL(rs.getString(3));
                dbOutboxInfo.setCollectorId(rs.getString(4));
                dbOutboxInfo.setCollectorName(rs.getString(5));
                dbOutboxInfo.setCollectorPhotoURL(rs.getString(6));
                dbOutboxInfo.setSenderId(rs.getString(7));
                dbOutboxInfo.setSenderName(rs.getString(8));
                dbOutboxInfo.setSenderPhotoURL(rs.getString(9));
                dbOutboxInfo.setContent(rs.getString(10));
                dbOutboxInfo.setRepliedAt(rs.getTimestamp(11));
                dbOutboxInfo.setUpdatedAt(rs.getTimestamp(12));
            }
            return dbOutboxInfo;
        }
        return null; //By default if connection to database fails, method will return null
    }

    @Override
    public List<OutboxInfo> getAll() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getFetchAllDataQuery());
            rs = ps.executeQuery();

            List<OutboxInfo> outboxList = new ArrayList<>();
            while (rs.next()) {
                OutboxInfo dbOutboxInfo = new OutboxInfo();
                dbOutboxInfo.setMailId(rs.getString(1));
                dbOutboxInfo.setType(rs.getString(2));
                dbOutboxInfo.setImageURL(rs.getString(3));
                dbOutboxInfo.setCollectorId(rs.getString(4));
                dbOutboxInfo.setCollectorName(rs.getString(5));
                dbOutboxInfo.setCollectorPhotoURL(rs.getString(6));
                dbOutboxInfo.setSenderId(rs.getString(7));
                dbOutboxInfo.setSenderName(rs.getString(8));
                dbOutboxInfo.setSenderPhotoURL(rs.getString(9));
                dbOutboxInfo.setContent(rs.getString(10));
                dbOutboxInfo.setRepliedAt(rs.getTimestamp(11));
                dbOutboxInfo.setUpdatedAt(rs.getTimestamp(12));
                outboxList.add(dbOutboxInfo);
            }
            return outboxList;
        }
        return null; //By default if connection to database fails, method will return null
    }

}
