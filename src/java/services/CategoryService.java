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
import models.Category;
import queries.CategoryQueryHandler;

/**
 *
 * @author Oshadee
 */
public class CategoryService implements ServiceInterface<Category> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error

    @Override
    public boolean add(Category category) throws ClassNotFoundException, SQLException {
        if (DBConnection.getInstance() != null) {
            Connection con = DBConnection.getConnction();
            ps = con.prepareStatement(CategoryQueryHandler.getAddDataQuery());
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            eResult = ps.executeUpdate();

            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Category type) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Category category) throws ClassNotFoundException, SQLException {
        if (DBConnection.getInstance() != null) {
            Connection con = DBConnection.getConnction();
            ps = con.prepareStatement(CategoryQueryHandler.getRemoveDataQuery());
            ps.setString(1, category.getName());
            eResult = ps.executeUpdate();

            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public Category get(Category type) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> search() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
