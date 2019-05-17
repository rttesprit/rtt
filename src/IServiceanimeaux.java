package edu.fundup.model.iservice;


import java.util.ArrayList;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;


public interface IServiceanimeaux {

	public void create(Animeaux animeaux) throws  Exception;
	public void update(Animeaux animeaux) throws DataBaseException;
	public Animeaux getById(int codeB) throws DataBaseException;
	public ArrayList<Animeaux> getAll() throws  Exception;
	public void delete(Animeaux animeaux) throws DataBaseException;

}