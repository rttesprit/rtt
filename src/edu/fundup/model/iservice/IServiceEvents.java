/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.iservice;

import edu.fundup.model.entity.Events;
import edu.fundup.model.entity.JoinEvents;
import edu.fundup.model.entity.RatingEvents;
import java.util.ArrayList;

/**
 *
 * @author lhannachi
 */
public interface IServiceEvents {
    public void add(Events e);
    public void update(Events e,int id_event);
    public void remove(int id_event);
    
    public void rating(RatingEvents e);
    public void updateRating(RatingEvents e,int idevent, int iduser);
    public boolean testRaitngs(int idevent, int iduser);
    public Double getRating(int idevent, int iduser);
    
    public void join(int idevent, int iduser);
    public boolean alreadyJoin(int idevent, int iduser);
    public ArrayList<JoinEvents> getAllJoiners(int idevent);
    
    public ArrayList<Events> getAll();
    public ArrayList<Events> findByLocation(String location);
    public ArrayList<Events> findByCategorie(String categorie);
    public ArrayList<Events> findByTitre(String titre);
    public Events findById(int id_event);
    
    public ArrayList<RatingEvents> getAllRating();

    
   
}
