/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.iservice;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Categorie;
import edu.fundup.model.entity.Post;
import java.util.ArrayList;

/**
 *
 * @author hhamzaoui
 */
public interface IServiceCategorie {

    public void create(Categorie c) throws DataBaseException;

    public void modifier(int id) throws DataBaseException;

    public void delete(Categorie t) throws DataBaseException;

    public Post getById(Categorie t, int id) throws DataBaseException;

    public ArrayList<Categorie> getAll() throws DataBaseException;

}
