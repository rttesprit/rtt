/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.service;

import edu.fundup.model.entity.Events;
import edu.fundup.model.entity.JoinEvents;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.RatingEvents;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.utils.DataSource;
import edu.fundup.utils.UserSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lhannachi
 */
public class ServiceEvents implements IServiceEvents {
    Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;

    public ServiceEvents() {
        connection = dataSource.getInstance().getConnection();
    }

    @Override
    public void add(Events e) {

        String req = "INSERT INTO event (idevent,iduser,idcat,file,date_event,description,lieu,titre,categorie,participant,montant) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, e.getId_event());
            preparedStatement.setInt(2, e.getId_user());
            preparedStatement.setInt(3, e.getId_categorie());
            preparedStatement.setString(4, e.getFile_url());
            preparedStatement.setDate(5, (Date) e.getEvent_date());
            preparedStatement.setString(6, e.getDescription());
            preparedStatement.setString(7, e.getLocation());
            preparedStatement.setString(8, e.getTitle());
            preparedStatement.setString(9, e.getCategorie());
            preparedStatement.setInt(10, e.getParticipant());
            preparedStatement.setFloat(11, e.getMontant());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();           
        } 
    }

    @Override   
    public void update(Events e, int id_event) {
        String req = "UPDATE event SET iduser=?,idcat =?,file=?,date_event=?,description=?,lieu=?,titre=?,categorie=?,participant=?,montant=? WHERE idevent="+id_event;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, e.getId_user());
            preparedStatement.setInt(2, e.getId_categorie());
            preparedStatement.setString(3, e.getFile_url());
            preparedStatement.setDate(4, (Date) e.getEvent_date());
            preparedStatement.setString(5, e.getDescription());
            preparedStatement.setString(6, e.getLocation());
            preparedStatement.setString(7, e.getTitle());
            preparedStatement.setString(8, e.getCategorie());
            preparedStatement.setInt(9, e.getParticipant());
            preparedStatement.setFloat(10, e.getMontant());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(int id_event) {
        // find by id
        String req = "DELETE FROM event WHERE idevent ="+id_event;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error lors de la supression" + ex.getMessage());
        }
    }

    @Override
    public ArrayList<Events> getAll() {
        String req = "SELECT * FROM event";
        ArrayList<Events> evenements = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Events evenement = new Events(rs.getInt("idevent"), rs.getInt("iduser"), rs.getInt("idcat"), rs.getString("titre"), rs.getDate("date_event"), rs.getString("description"), rs.getString("lieu"), rs.getString("file"), rs.getString("categorie"), rs.getInt("participant"), rs.getFloat("montant"));
                evenements.add(evenement);
                
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenements;
    }

    @Override
    public Events findById(int id_event) {
        String reqSql = "SELECT * FROM event WHERE idevent = ?";
        Events ev = new Events();
        try {
            ps = connection.prepareStatement(reqSql);
            ps.setInt(1, id_event);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ev.setId_event(rs.getInt("idevent"));
                ev.setId_user(rs.getInt("iduser"));
                ev.setId_categorie(rs.getInt("idcat"));
                ev.setTitle(rs.getString("titre"));
                ev.setEvent_date(rs.getDate("date_event"));
                ev.setDescription(rs.getString("description"));
                ev.setLocation(rs.getString("lieu"));
                ev.setFile_url(rs.getString("file"));
                ev.setCategorie(rs.getString("categorie"));
                ev.setParticipant(rs.getInt("participant"));
                ev.setMontant(rs.getFloat("montant"));
               
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return ev;
    }

    @Override
    public ArrayList<Events> findByLocation(String location) {
        String reqSql = "SELECT * FROM event WHERE lieu = ?";
        Events ev = new Events();
        ArrayList<Events> evenements = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            ps = connection.prepareStatement(reqSql);
            ps.setString(1, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ev.setId_event(rs.getInt("idevent"));
                ev.setId_user(rs.getInt("iduser"));
                ev.setId_categorie(rs.getInt("idcat"));
                ev.setTitle(rs.getString("titre"));
                ev.setEvent_date(rs.getDate("date_event"));
                ev.setDescription(rs.getString("description"));
                ev.setLocation(rs.getString("lieu"));
                ev.setFile_url(rs.getString("file"));
                ev.setCategorie(rs.getString("categorie"));
                ev.setParticipant(rs.getInt("participant"));
                ev.setMontant(rs.getFloat("montant"));
                evenements.add(ev);
               
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return evenements;
    }

    @Override
    public ArrayList<Events> findByCategorie(String categorie) {
        String reqSql = "SELECT * FROM event WHERE categorie = ?";
        Events ev = new Events();
        ArrayList<Events> evenements = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            ps = connection.prepareStatement(reqSql);
            ps.setString(1, categorie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ev.setId_event(rs.getInt("idevent"));
                ev.setId_user(rs.getInt("iduser"));
                ev.setId_categorie(rs.getInt("idcat"));
                ev.setTitle(rs.getString("titre"));
                ev.setEvent_date(rs.getDate("date_event"));
                ev.setDescription(rs.getString("description"));
                ev.setLocation(rs.getString("lieu"));
                ev.setFile_url(rs.getString("file"));
                ev.setCategorie(rs.getString("categorie"));
                ev.setParticipant(rs.getInt("participant"));
                ev.setMontant(rs.getFloat("montant"));
                evenements.add(ev);
               
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return evenements;
    }

    @Override
    public ArrayList<Events> findByTitre(String titre) {
        String reqSql = "SELECT * FROM event WHERE titre =?";
        Events ev = new Events();
        ArrayList<Events> evenements = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            ps = connection.prepareStatement(reqSql);
            ps.setString(1, titre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ev.setId_event(rs.getInt("idevent"));
                ev.setId_user(rs.getInt("iduser"));
                ev.setId_categorie(rs.getInt("idcat"));
                ev.setTitle(rs.getString("titre"));
                ev.setEvent_date(rs.getDate("date_event"));
                ev.setDescription(rs.getString("description"));
                ev.setLocation(rs.getString("lieu"));
                ev.setFile_url(rs.getString("file"));
                ev.setCategorie(rs.getString("categorie"));
                ev.setParticipant(rs.getInt("participant"));
                ev.setMontant(rs.getFloat("montant"));
                evenements.add(ev);
               
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return evenements;
    }

   

    @Override
    public ArrayList<RatingEvents> getAllRating() {
      
        String req = "SELECT * FROM rating";
        ArrayList<RatingEvents> ratings = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RatingEvents r = new RatingEvents(rs.getInt("idevent"), rs.getInt("iduser"), rs.getDouble("value"));
                ratings.add(r);
                  }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;  
    }

    @Override
    public void updateRating(RatingEvents r,int idevent, int iduser) {
        String req = "UPDATE rating SET idevent=?,iduser =?,value=? WHERE idevent="+idevent+" AND iduser="+iduser;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r.getId_event());
            preparedStatement.setInt(2, r.getId_user());
            preparedStatement.setDouble(3, r.getValue());
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean testRaitngs(int idevent, int iduser) {
        String req = "SELECT * FROM rating WHERE idevent="+idevent+" AND iduser="+iduser;
       
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               return true;
                  }
            return false;
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public void join(int idevent, int iduser) {
        String req = "INSERT INTO participer (idevent,iduser) VALUES (?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, idevent);
            preparedStatement.setInt(2, iduser);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();           
        } 
    }

    @Override
    public boolean alreadyJoin(int idevent, int iduser) {
         String req = "SELECT * FROM participer WHERE idevent= "+idevent+" AND iduser="+iduser;
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               return true;
                  }
            return false;
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public Double getRating(int idevent, int iduser) {
        String req = "SELECT value FROM rating WHERE idevent= "+idevent+" AND iduser= "+iduser;
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getDouble("value");
                                      }
                    } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public ArrayList<JoinEvents> getAllJoiners(int idevent) {
        String req = "SELECT * FROM participer WHERE idevent= "+idevent;
        JoinEvents je = new JoinEvents();
        ArrayList<JoinEvents> joinEvents = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
             preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                je.setId_event(rs.getInt("idevent"));
                je.setId_user(rs.getInt("iduser"));
                joinEvents.add(je);
               
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return joinEvents;
    }

    @Override
    public void rating(RatingEvents e) {
        String req = "INSERT INTO rating (idevent,iduser,value) VALUES (?,?,?)";
        PreparedStatement preparedStatement;
        Member m = UserSession.getInstance().getMember();
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, e.getId_event());
            preparedStatement.setInt(2, m.getId());
            preparedStatement.setDouble(3, e.getValue());
            

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();           
        }    
    }

    @Override
    public int compter(int idevent) {
        String req = "SELECT count(*) FROM participer WHERE idevent ="+idevent;
        int somme =0;
        PreparedStatement preparedStatement;
        try {
             preparedStatement = connection.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                somme = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return somme;
    }
    

   

    
   
}

