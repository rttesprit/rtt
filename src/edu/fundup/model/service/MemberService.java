/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.service;

import edu.fundup.model.entity.Member;
import edu.fundup.model.iservice.IMemberService;
import edu.fundup.utils.DataSource;
import edu.fundup.utils.UserSession;

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
    String role;


    public MemberService() {
        connection = dataSource.getInstance().getConnection();
    }


    @Override
    public int SignIn(Member m) throws SQLException {
        boolean b = false;
        String query = "SELECT * FROM member WHERE login=? and password= ?";
        try
        {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getlogin());
            pst.setString(2, m.getPassword());
            rs = pst.executeQuery();
            } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        b = rs.next(); // if user exists
        System.out.println("user exists : "+ b);

        if (b == true && (rs.getInt("enable")==1) ) {
            System.out.println("userConnectedTreatement");
            // TO DO TO GET INFO AFTER LOGIN
            Member connectedMember = new Member(rs.getInt("id"),rs.getString("role"),rs.getString("login"),rs.getString("name"),rs.getString("mail"),rs.getString("password"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"),rs.getString("city"),rs.getString("payment_type"),rs.getString("credit_card_number"),rs.getString("cvv_num"),rs.getString("president"),rs.getString("foundation_date"),rs.getInt("enable"),rs.getString("photo_path"),rs.getString("register_date"));
            UserSession.getInstance().setMember(connectedMember);
            System.out.println(connectedMember.toString());
        }
        else{
            System.out.println("mdp wala login ghalet, wala compte désactivé");
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
        String md5 = m.getPassword();
        String query = "INSERT INTO member (login,mail,password,first_name,last_name,address,city,country,photo_path,role,register_date) VALUES (?,?,?,?,?,?,?,?,?,?,CURRENT_DATE)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getlogin());
            pst.setString(2, m.getmail());
            pst.setString(3, md5);
            pst.setString(4, m.getfirst_name());
            pst.setString(5, m.getlast_name());
            pst.setString(6, m.getAddress());
            pst.setString(7, m.getCity());
            pst.setString(8, m.getCountry());
            pst.setString(9, m.getPhoto());
            pst.setString(10,m.getRole());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void RegisterContributor(Member m) {
        String query = "INSERT INTO member (login,mail,password,first_name,last_name,address,city,country,photo_path,payment_type,credit_card_number,cvv_num,role,register_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE)";
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

            // Payment INFOS
            pst.setString(10, m.getPayment_type());
            pst.setString(11, m.getCredit_card_number());
            pst.setString(12, m.getCvv_num());
            pst.setString(13,m.getRole());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void RegisterEntreprise(Member m) {
        String query = "INSERT INTO member (login,mail,password,name,address,city,country,photo_path,payment_type,credit_card_number,cvv_num,president,foundation_date,register_date,role) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getlogin());
            pst.setString(2, m.getmail());
            pst.setString(3, m.getPassword());
            pst.setString(4, m.getName());
            pst.setString(5, m.getAddress());
            pst.setString(6, m.getCity());
            pst.setString(7, m.getCountry());
            pst.setString(8, m.getPhoto());

            // Payment INFOS
            pst.setString(9, m.getPayment_type());
            pst.setString(10, m.getCredit_card_number());
            pst.setString(11, m.getCvv_num());

            // Entreprise INFOS
            pst.setString(12, m.getPresident());
            pst.setString(13, m.getFoundation_date());
            pst.setString(14,m.getRole());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
    // Paperless / Contributor or Entreprise
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
        else if (role.equals("Contributor")) {
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
                    // TO DO CONSTRUCTOR
                    /*
                    members.add(new Member(rs.getString("login"),rs.getString("name"), rs.getString("password"),rs.getString("mail"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getInt("enable"), rs.getString("description"),rs.getString("photo_path"),rs.getString("payment_type"),rs.getString("credit_card_number"),rs.getString("cvv_num"),rs.getString("president"),rs.getString("foundation_date")));
                    */
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
