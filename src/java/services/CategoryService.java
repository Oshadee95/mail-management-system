/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import servers.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import queries.CategoryQueryHandler;
import queries.QueryHandlerInterface;

/**
 *
 * @author Oshadee
 */
public class CategoryService implements ServiceInterface<Category> {

    private PreparedStatement ps;
    private ResultSet rs;
    private int eResult; // execution result will either return 1 for successful execution and 0 for error
    QueryHandlerInterface categoryQueryHandler = new CategoryQueryHandler();

    @Override
    public boolean add(Category category) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(categoryQueryHandler.getAddDataQuery());
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean update(Category category) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(categoryQueryHandler.getUpdateDataQuery());
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public boolean remove(Category category) throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(categoryQueryHandler.getRemoveDataQuery());
            ps.setInt(1, category.getId());
            eResult = ps.executeUpdate();
            return (eResult == 1); //This will return true if eResult is 1 and false if 0
        }
        return false; //By default if connection to database fails, method will return false
    }

    @Override
    public Category get(Category category) throws ClassNotFoundException, SQLException {
        return null; // Not necesserily required 
    }

    @Override
    public List<Category> getAll() throws ClassNotFoundException, SQLException {
        if (DB.getInstance() != null) {
            Connection con = DB.getConnction();
            ps = con.prepareStatement(categoryQueryHandler.getFetchAllDataQuery());
            rs = ps.executeQuery();

            List<Category> categoryList = new ArrayList<>();
            while (rs.next()) {
                Category dbCategory = new Category();
                dbCategory.setId(rs.getInt(1));
                dbCategory.setName(rs.getString(2));
                dbCategory.setDescription(rs.getString(3));
                categoryList.add(dbCategory);
            }
            return categoryList;
        }
        return null; //By default if connection to database fails, method will return null
    }
}
