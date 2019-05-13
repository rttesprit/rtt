package edu.fundup.model.service;

import edu.fundup.model.entity.Notification;
import edu.fundup.model.iservice.INotificationService;
import edu.fundup.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class NotificationService implements INotificationService {

    Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;


    public NotificationService() {
        connection = dataSource.getInstance().getConnection();

    }

    @Override
    public void addNotification(Notification notification) {

        try {
            String query = "INSERT INTO `notification`(id_membre, object) VALUES ( ?, ?);";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,notification.getId_user());
  //          ps.setTimestamp(2, new Timestamp(notification.getCreation_date().getTime()));

            ps.setString(2,notification.getObject());
//            ps.setInt(4,notification.getIs_read());

            System.out.println(ps);
            ps.executeUpdate();
            System.out.println("a Notification has been added successfully ");


        } catch (SQLException e) {
            System.out.println("erreur lors de l'insertion d'une notifications" + e.getMessage());
        }

    }

    @Override
    public void markNotificationAsRead(Notification notification) {

        try {
            String query= "UPDATE `notification` SET" +
                    "`is_read` = 1 "+


                    "WHERE `notification`.`idnotif` = ?;";


            PreparedStatement   ps = connection.prepareStatement(query);


            ps.setInt(1,notification.getId());
            notification.setIs_read(1);

            ps.executeUpdate();
            System.out.println("The notification marked as read ");

        } catch (SQLException e) {
            System.out.println("erreur at marking notificattion as read " + e.getMessage());
        }





    }

    @Override
    public ArrayList<Notification> getNotifications() {

        ArrayList<Notification> listNotifications = new ArrayList<>();

        try {
            String query = "select * from `notification`;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Notification notification  = new Notification();
                notification.setId(rs.getInt(1));
                notification.setId_user(rs.getInt(2));
                notification.setCreation_date(rs.getDate(3));
                notification.setObject(rs.getString(4));
                notification.setIs_read(rs.getInt(5));


                listNotifications.add(notification);

            }
            return listNotifications;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;

    }

    @Override
    public ArrayList<Notification> findNotificationByIdUser(int idUser) {
        ArrayList<Notification> listNotifications = new ArrayList<>();

        try {
            String query = "select * from `notification` where id_membre=?;";
            PreparedStatement   ps = connection.prepareStatement(query);
            ps.setInt(1,idUser);



            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {

                Notification notification  = new Notification();
                notification.setId(rs.getInt(1));
                notification.setId_user(rs.getInt(2));
                notification.setCreation_date(rs.getDate(3));
                notification.setObject(rs.getString(4));
                notification.setIs_read(rs.getInt(5));


                listNotifications.add(notification);

            }
            return listNotifications;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;
    }

    @Override
    public ArrayList<Notification> findNotificationByTime(Date date) {
        ArrayList<Notification> listNotifications = new ArrayList<>();

        try {
            String query = "select * from `notification` where date_creation=?;";
            PreparedStatement   ps = connection.prepareStatement(query);
            ps.setTimestamp(1, new Timestamp(date.getTime()));



            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {

                Notification notification  = new Notification();
                notification.setId(rs.getInt(1));
                notification.setId_user(rs.getInt(2));
                notification.setCreation_date(rs.getDate(3));
                notification.setObject(rs.getString(4));
                notification.setIs_read(rs.getInt(5));


                listNotifications.add(notification);

            }
            return listNotifications;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;

    }

    @Override
    public ArrayList<Notification> getUnreadNotifications() {

        ArrayList<Notification> listNotifications = new ArrayList<>();

        try {
            String query = "select * from `notification` where is_read=0;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Notification notification  = new Notification();
                notification.setId(rs.getInt(1));
                notification.setId_user(rs.getInt(2));
                notification.setCreation_date(rs.getDate(3));
                notification.setObject(rs.getString(4));
                notification.setIs_read(rs.getInt(5));


                listNotifications.add(notification);

            }
            return listNotifications;
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());        }





        return null;

    }
}
