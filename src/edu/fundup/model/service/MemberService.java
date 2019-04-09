/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.service;

import edu.fundup.model.entity.Member;
import edu.fundup.model.iservice.IMemberService;
import edu.fundup.utils.DataSource;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Guideinfo
 */
public class MemberService implements IMemberService {

    private DataSource dataSource;
    private Statement stm;
    private PreparedStatement pst;
    private ResultSet rs;
    Connection connection;


    public MemberService() {
        connection = dataSource.getInstance().getConnection();
    }


    @Override
    public int SignIn(Member m) throws SQLException {
        String query = "SELECT * FROM member WHERE login=? and password= ?";
        pst = connection.prepareStatement(query);
        pst.setString(1, m.getlogin());
        pst.setString(2, m.getPassword());
        rs = pst.executeQuery();

        boolean v = rs.next(); // if exists

        if (v == true && (rs.getInt("enable")==1) ) {
            System.out.println("userConnectedTreatement");
             m = new Member(rs.getInt("id"),rs.getString("login"),rs.getString("name"), rs.getString("password"),rs.getString("mail"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getInt("enable"), rs.getString("description"),rs.getString("photo_path"),rs.getString("payment_type"),rs.getString("credit_card_number"),rs.getString("cvv_num"),rs.getString("president"),rs.getString("foundation_date"));
            //FundUp.USER_ONLINE=m;
        return 1;

        }

        return 0; // compte utilisateur n'existe pas
    }


    // not finished yet

    @Override
    public void forgotPwd(Member m) {
        String query = "SELECT * FROM member WHERE id=" + m.getId();
    }


    @Override
    public void RegisterPaperlessMember(Member m) {
        //String md5 = m.getPassword();
        String role = "Paperles member";
        String query = "INSERT INTO member (login,mail,password,first_name,last_name,address,city,country,register_date,role,photo_path) VALUES (?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,role,?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getlogin());
            pst.setString(2, m.getmail());
            pst.setString(3, m.getPassword());
            pst.setString(4, m.getfirst_name());
            pst.setString(5, m.getlast_name());
            pst.setString(6, m.getAddress());
            pst.setString(7, m.getCity());
            pst.setString(8, m.getCountry());
            pst.setString(9, m.getPhoto());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void RegisterContributor(Member m) {
        String query = "INSERT INTO member (login,mail,password,first_name,last_name,address,city,country,register_date,role,photo_path,payment_type,credit_card_number,cvv_num) VALUES (?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getlogin());
            pst.setString(2, m.getmail());
            pst.setString(3, m.getPassword());
            pst.setString(4, m.getfirst_name());
            pst.setString(5, m.getlast_name());
            pst.setString(6, m.getAddress());
            pst.setString(7, m.getCity());
            pst.setString(8, m.getCountry());
            pst.setString(9, m.getRole());
            pst.setString(10, m.getPhoto());

            // Payment INFOS
            pst.setString(11, m.getPayment_type());
            pst.setString(12, m.getCredit_card_number());
            pst.setString(13, m.getCvv_num());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void RegisterEntreprise(Member m) {
        String query = "INSERT INTO member (login,mail,password,name,address,city,country,register_date,role,photo_path,payment_type,credit_card_number,cvv_num,president,foundation_date) VALUES (?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getlogin());
            pst.setString(2, m.getmail());
            pst.setString(3, m.getPassword());
            pst.setString(4, m.getName());
            pst.setString(5, m.getAddress());
            pst.setString(6, m.getCity());
            pst.setString(7, m.getCountry());
            pst.setString(8, m.getRole());
            pst.setString(9, m.getPhoto());

            // Payment INFOS
            pst.setString(10, m.getPayment_type());
            pst.setString(11, m.getCredit_card_number());
            pst.setString(12, m.getCvv_num());

            // Entreprise INFOS
            pst.setString(13, m.getPresident());
            pst.setString(14, m.getFoundation_date());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public ArrayList<Member> getByRole(String role) {
        ArrayList<Member> members = new ArrayList<>();

        if (role.equals("Paperless member")) {
            String query = "SELECT * FROM member WHERE role='" + role + "'";
            try {
                pst = connection.prepareStatement(query);
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    members.add(new Member(rs.getString("login"), rs.getString("password"),rs.getString("mail"),rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getString("photo_path")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (role.equals("Contributor member")) {
            String query = "SELECT * FROM member WHERE role='" + role + "'";
            try {
                pst = connection.prepareStatement(query);
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    members.add(new Member(rs.getString("login"), rs.getString("password"),rs.getString("mail"),rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getString("photo_path"),rs.getString("payment_type"),rs.getString("credit_card_number"),rs.getString("cvv_num")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else //Entreprise case
        {
            String query = "SELECT * FROM member WHERE role='" + role + "'";
            try {
                pst = connection.prepareStatement(query);
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    members.add(new Member(rs.getString("login"),rs.getString("name"), rs.getString("password"),rs.getString("mail"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getInt("enable"), rs.getString("description"),rs.getString("photo_path"),rs.getString("payment_type"),rs.getString("credit_card_number"),rs.getString("cvv_num"),rs.getString("president"),rs.getString("foundation_date")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return members;
    }

    //MD5 Encryption password for DataBase
    public String getMd5(String pwd) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input(pwd) digest() return array of byte
            byte[] messageDigest = md.digest(pwd.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
