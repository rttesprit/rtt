package edu.fundup.model.iservice;

import java.util.ArrayList;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Comments;

public interface IServiceComment {
	public void create(Comments comments) throws  Exception;
	public void update(Comments comments) throws DataBaseException;
 	public ArrayList<Comments> getAll() throws  Exception;
	public void delete(Comments comments) throws DataBaseException;
}
