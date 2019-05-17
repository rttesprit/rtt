package edu.fundup.model.iservice;

import java.util.ArrayList;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.PostAnimalSupport;


public interface IServiceSupport {
	public void create(PostAnimalSupport comments) throws  Exception;
	public void update(PostAnimalSupport comments) throws DataBaseException;
 	public ArrayList<PostAnimalSupport> getAll() throws  Exception;
	public void delete(PostAnimalSupport comments) throws DataBaseException;

}
