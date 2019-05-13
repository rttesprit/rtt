package edu.fundup.model.service;



import edu.fundup.model.entity.TypeReclamation;
import edu.fundup.model.iservice.ITypeReclamation;
import edu.fundup.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class TypeReclamationService implements ITypeReclamation {

    Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;


    public TypeReclamationService() {
        connection = dataSource.getInstance().getConnection();

    }


    @Override
    public ArrayList<TypeReclamation> getTypeReclmationList() {
        ArrayList<TypeReclamation> listTypeReclamations = new ArrayList<>();
        try {
            String query = "select * from `typereclamation`;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TypeReclamation tr = new TypeReclamation();
                tr.setId(rs.getInt(1));
                tr.setDescription(rs.getString(2));
                tr.setObjet(rs.getString(3).toUpperCase());

                listTypeReclamations.add(tr);

            }
            return listTypeReclamations;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;
    }

    @Override
    public TypeReclamation getTypeReclmation(int id) {

        String query = "SELECT * FROM `typereclamation` WHERE `id`='"+id+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();


            TypeReclamation tr = new TypeReclamation();
            while (rs.next()) {


                tr.setId(rs.getInt(1));
                tr.setDescription(rs.getString(2));


            }
            return tr;

        } catch (SQLException ex) {

            System.out.println("erreur getTypeReclmation" + ex.getMessage());
        }
        return null;
    }

    @Override
    public void addType(TypeReclamation tr) {
        if(tr.getObjet().equalsIgnoreCase("post") || tr.getObjet().equalsIgnoreCase("event"))
        try {
            String query = "INSERT INTO `typereclamation` VALUES (null, ? ,? );";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,tr.getDescription());
            ps.setString(2,tr.getObjet());


            System.out.println(ps);
            ps.executeUpdate();
            System.out.println("a typeReclamation has been added successfully ");


        } catch (SQLException e) {
            System.out.println("erreur lors de l'insertion d'une typereclamation" + e.getMessage());
        }

    }

    @Override
    public void updateType(TypeReclamation tr) {


    }

    @Override
    public void deleteType(TypeReclamation tr) {

    }

    @Override
    public ArrayList<TypeReclamation> getTypeReclamationListWithType(String type) {
        ArrayList<TypeReclamation> listTypeReclamations = new ArrayList<>();

        String query = "SELECT * FROM `typereclamation` WHERE `objet`='"+type+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                TypeReclamation tr = new TypeReclamation();
                tr.setId(rs.getInt(1));
                tr.setDescription(rs.getString(2));
                listTypeReclamations.add(tr);
                System.out.println(tr);

            }
            return listTypeReclamations;

        } catch (SQLException ex) {

            System.out.println("erreur getTypeReclmation" + ex.getMessage());
        }
        return null;
    }
}
