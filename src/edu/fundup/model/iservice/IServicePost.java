/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.iservice;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Post;
import java.util.ArrayList;

/**
 *
 * @author hhamzaoui
 */
public interface IServicePost {

    public void create(Post c) throws DataBaseException;

    public void modifier(int id) throws DataBaseException;

    public void delete(Post t) throws DataBaseException;

    public Post getById(Post t, int id) throws DataBaseException;

    public ArrayList<Post> getAll() throws DataBaseException;

}
