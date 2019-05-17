package edu.fundup.model.iservice;

import java.util.ArrayList;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.entity.PostAnimalSettings;



public interface IServiceSettings {
	  void create(PostAnimalSettings comments) throws  Exception;
	  void update(PostAnimalSettings comments) throws DataBaseException;
 	  ArrayList<PostAnimalSettings> getAll() throws  Exception;
	  void delete(PostAnimalSettings comments) throws DataBaseException;
	  PostAnimalSettings getByPostId(Animeaux a)throws DataBaseException;
}
