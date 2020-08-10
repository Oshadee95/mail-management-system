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
import models.Outbox;
import queries.OutboxQueryHandler;
import servers.DB;
import queries.QueryHandlerInterface;

/**
 *
 * @author Oshadee
 */
public class OutboxService implements ServiceInterface<Outbox> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface outboxQueryHandler = new OutboxQueryHandler();
    
    @Override
    public boolean add(Outbox outbox) throws ClassNotFoundException, SQLException {
         if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getAddDataQuery());
            ps.setString(1, outbox.getMailId());
            ps.setString(2, outbox.getSenderId());
            ps.setString(3, outbox.getContent());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Outbox outbox) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Outbox outbox) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(outboxQueryHandler.getRemoveDataQuery());
            ps.setString(1, outbox.getMailId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public Outbox get(Outbox outbox) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Outbox> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
