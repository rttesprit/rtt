/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.service;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Post;
import edu.fundup.model.iservice.IServicePost;
import edu.fundup.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hhamzaoui
 */
public class ServicePost implements IServicePost {

    Connection conn = DataSource.getInstance().getConnection();

    @Override
    public void create(Post c) throws DataBaseException {
        try {
            String req = "INSERT INTO post (idcat, titre, description, image,lieu, jour_val,iduser,montant,enable) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, c.getIdcat());
            st.setString(2, c.getTitre());
            st.setString(3, c.getDescription());
            st.setString(4, c.getImage());
            st.setString(5, c.getLieu());
            st.setInt(6, c.getJours());
            st.setInt(7, c.getIdUser());
            st.setDouble(8, c.getMontant());
            st.setBoolean(9, true);

            st.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex.getMessage());

        }

    }

    @Override
    public void modifier(int id) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Post t) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post getById(Post t, int id) throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Post> getAll() throws DataBaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
