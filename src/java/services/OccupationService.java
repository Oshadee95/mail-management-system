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
import models.Occupation;
import queries.OccupationQueryHandler;
import servers.DB;
import queries.QueryHandlerInterface;

/**
 *
 * @author Oshadee
 */
public class OccupationService implements ServiceInterface<Occupation> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface occupationQueryHandler = new OccupationQueryHandler();
    
    @Override
    public boolean add(Occupation occupation) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(occupationQueryHandler.getAddDataQuery());
            ps.setString(1, occupation.getTitle());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Occupation occupation) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Occupation occupation) throws ClassNotFoundException, SQLException {
       if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(occupationQueryHandler.getRemoveDataQuery());
            ps.setInt(1, occupation.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public Occupation get(Occupation occupation) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Occupation> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
