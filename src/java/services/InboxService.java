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
import models.Inbox;
import queries.InboxQueryHandler;
import queries.QueryHandler;
import servers.DB;

/**
 *
 * @author Oshadee
 */
public class InboxService implements ServiceInterface<Inbox> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandler inboxQueryHandler = new InboxQueryHandler();

    @Override
    public boolean add(Inbox inbox) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getAddDataQuery());
            ps.setString(1, inbox.getId());
            ps.setInt(2, inbox.getCategoryId());
            ps.setString(3, inbox.getSender());
            ps.setString(4, inbox.getContent());
            ps.setString(5, inbox.getCollectorId());
            ps.setString(6, inbox.getRecipientId());
            ps.setString(7, inbox.getImageURL());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Inbox inbox) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Inbox inbox) throws ClassNotFoundException, SQLException {
       if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(inboxQueryHandler.getRemoveDataQuery());
            ps.setString(1, inbox.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public Inbox get(Inbox inbox) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inbox> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
