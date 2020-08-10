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
import models.Role;
import queries.RoleQueryHandler;
import servers.DB;
import queries.QueryHandlerInterface;

/**
 *
 * @author Oshadee
 */
public class RoleService  implements ServiceInterface<Role> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface roleQueryHandler = new RoleQueryHandler();

    @Override
    public boolean add(Role role) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(roleQueryHandler.getAddDataQuery());
            ps.setString(1, role.getId());
            ps.setInt(2, role.getWeight());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Role role) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Role role) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(roleQueryHandler.getRemoveDataQuery());
            ps.setString(1, role.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public Role get(Role role) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
