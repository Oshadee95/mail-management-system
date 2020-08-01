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
import models.User;
import queries.QueryHandler;
import queries.UserQueryHandler;
import servers.DB;

/**
 *
 * @author Oshadee
 */
public class UserService implements ServiceInterface<User> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandler userQueryHandler = new UserQueryHandler();

    @Override
    public boolean add(User user) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getAddDataQuery());
            ps.setString(1, user.getNic());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getDislayName());
            ps.setInt(4, user.getOccupationId());
            ps.setString(5, user.getOffice());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getPassword());

            eResult = ps.executeUpdate();

            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(User user) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(User user) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(userQueryHandler.getRemoveDataQuery());
            ps.setInt(1, user.getId());
            eResult = ps.executeUpdate();

            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public User get(User user) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
