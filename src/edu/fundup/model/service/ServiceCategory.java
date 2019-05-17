/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.service;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Categorie;
import edu.fundup.model.entity.Post;
import edu.fundup.model.iservice.IServiceCategorie;
import edu.fundup.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hhamzaoui
 * @param <Categorie>
 */
public class ServiceCategory implements IServiceCategorie {
    Connection conn = DataSource.getInstance().getConnection();

    @Override
    public void create(Categorie c) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(int id) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Categorie t) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post getById(Categorie t, int id) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getAll() throws DataBaseException {
         ArrayList<Categorie> cats = new ArrayList<>();
        String req = "select * from categorie";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categorie c=new Categorie(resultSet.getInt("idcat"),resultSet.getInt("idsoucat"), resultSet.getString("nomcat"), resultSet.getString("description"));              
                cats.add(c);
            }
        } catch (SQLException ex) {
            throw new DataBaseException(ex.getMessage());
        }
        return cats;
    }
                


    

  
    
}
