package edu.fundup.model.service;



import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.iservice.IReclamationService;
import edu.fundup.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class ReclamationService implements IReclamationService {

    Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;


    public ReclamationService() {
        connection = dataSource.getInstance().getConnection();
    }

    @Override
    public void addReclamation(Reclamation reclamation) {

        try {
            String query = "INSERT INTO `reclamation` VALUES (null, ?, ?, ?,?,? );";

            PreparedStatement ps = connection.prepareStatement(query);


            ps.setInt(1,reclamation.getIduser());
            ps.setInt(2,reclamation.getIdobjet());
            ps.setInt(3,reclamation.getIdtr());
            ps.setTimestamp(4, new Timestamp(reclamation.getDate().getTime()));
            ps.setString(5,reclamation.getEtat());

            System.out.println(ps);
            ps.executeUpdate();
            System.out.println("a Reclamation has been added successfully ");


        } catch (SQLException e) {
            System.out.println("erreur lors de l'insertion d'une reclamation" + e.getMessage());
        }

    }

    @Override
    public void updateReclamation( Reclamation reclamation) {
        try {
      String query= "UPDATE `reclamation` SET" +
              "`iduser` = ?, "+
              "`idobjet` = ?, " +
              "`idtr` = ?, " +
              "`date` = ? ," +
              "`etat` =? "+

              "WHERE `reclamation`.`idrec` = ?;";


            PreparedStatement   ps = connection.prepareStatement(query);
            ps.setInt(1,reclamation.getIduser());
            ps.setInt(2,reclamation.getIdobjet());
            ps.setInt(3,reclamation.getIdtr());
            ps.setTimestamp(4, new Timestamp(reclamation.getDate().getTime()));
            ps.setString(5,reclamation.getEtat());

            ps.setInt(6,reclamation.getId());


            ps.executeUpdate();
            System.out.println("a Reclamation has been updated ");

        } catch (SQLException e) {
            System.out.println("erreur lors de le mise à jour de Reclamation " + e.getMessage());
        }




    }



    @Override
    public void deleteReclamation(Reclamation reclamation) {
        String query = "DELETE FROM `reclamation` WHERE `id`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, reclamation.getId());
            ps.executeUpdate();
            System.out.println("a reclamation has been deleted");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public ArrayList<Reclamation> getReclamations() {
        ArrayList<Reclamation> listReclamations = new ArrayList<>();
        try {
        String query = "select * from `reclamation`;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                setReclamation(rs, reclamation);


                listReclamations.add(reclamation);

            }
            return listReclamations;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;
    }

    private void setReclamation(ResultSet rs, Reclamation reclamation) throws SQLException {
        reclamation.setId(rs.getInt(1));
        reclamation.setIduser(rs.getInt(2));
        reclamation.setIdobjet(rs.getInt(3));
        reclamation.setIdtr(rs.getInt(4));
        reclamation.setDate(rs.getDate(5));
        reclamation.setEtat(rs.getString(6));
    }

    @Override
    public Reclamation findReclamationById(int id) {

        String query = "SELECT * FROM `reclamation` WHERE `id`='"+id+"'";

        try {
            return getReclamation(query);
        } catch (SQLException ex) {

            System.out.println("erreur findReclamationById" + ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Reclamation> findReclamationByIdUser(int idUser) {


        ArrayList<Reclamation> listReclamations = new ArrayList<>();
        try {
            String query = "select * from `reclamation` where `iduser`="+idUser;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                setReclamation(rs, reclamation);


                listReclamations.add(reclamation);

            }
            return listReclamations;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;

    }


    @Override
    public Reclamation findReclamationByTypeObjet(String typeObjet) {
        String query = "SELECT * FROM `reclamation` WHERE `typeobjet`='"+typeObjet+"'";

        try {
            return getReclamation(query);
        } catch (SQLException ex) {

            System.out.println("erreur findReclamationByTypeObjet" + ex.getMessage());
        }
        return null;
    }

    private Reclamation getReclamation(String query) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        System.out.println(query);

        Reclamation reclamation = new Reclamation();
        while (rs.next()) {

            setReclamation(rs, reclamation);
            return reclamation;

        }


        return null;
    }

    @Override
    public Boolean reclamationExist(Reclamation reclamation){
        String query = "SELECT * FROM `reclamation`  where " +
                "`idobjet`="+reclamation.getIdobjet()+" AND `iduser`="+ reclamation.getIduser() +" AND `idtr`=" + reclamation.getIdtr();

        try {

            if (getReclamation(query) != null)
                return true;
            else
                return false;

        } catch (SQLException ex) {

            System.out.println("erreur reclamationExist" + ex.getMessage());
        }
        return null;


    }

    @Override
    public int reclamationCount(Reclamation reclamation) {
        try {


            String query = "SELECT count(*)  as total FROM `reclamation`  where " +
                    "`idobjet`=" + reclamation.getIdobjet() + " AND `idtr`=" + reclamation.getIdtr();
            System.out.println(query);
            Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            int a=0;
            while(rs.next()){
                a = rs.getInt("total");
                System.out.println("COUNT(*)="+a);
            }
            return a;



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
