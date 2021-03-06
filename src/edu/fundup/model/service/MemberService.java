/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.service;

import edu.fundup.controller.Acceuil;
import static edu.fundup.controller.Acceuil.seConnecterBtn;
import static edu.fundup.controller.Acceuil.userbox;
import edu.fundup.controller.FundUp;
import edu.fundup.controller.UserInfoBoxController;
import edu.fundup.controller.UserProfile;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.iservice.IMemberService;
import edu.fundup.utils.DataSource;
import edu.fundup.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

import java.io.IOException;
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
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, m.getLogin());
            pst.setString(2, m.getPassword());
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        b = rs.next(); // if user exists
        System.out.println("user exists : " + b);

        if (b == true && (rs.getInt("enable") == 1)) {

            System.out.println("userConnectedTreatement");

            Member connectedMember = new Member(rs.getInt("id"), rs.getString("role"), rs.getString("login"), rs.getString("name"), rs.getString("mail"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getString("payment_type"), rs.getString("credit_card_number"), rs.getString("cvv_num"), rs.getString("president"), rs.getString("foundation_date"), rs.getInt("enable"), rs.getString("photo_path"), rs.getString("register_date"));
            UserSession.getInstance().setMember(connectedMember);

            if (UserSession.getInstance().getMember().getRole().equalsIgnoreCase("Admin"))
            {
                HBox up = new UserProfile();
                FundUp.GLOBAL_PANE_BORDER.setCenter(up);
                System.out.println("admin connected");
            }

            UserInfoBoxController usbox = null;
            userbox.getChildren().remove(seConnecterBtn);
            
            try {
                usbox = new UserInfoBoxController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Acceuil.userbox.getChildren().add(usbox);

            System.out.println("USER ONLINE :" + UserSession.getInstance().getMember().toString());
        } else if (b == true && (rs.getInt("enable") == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Disabled Account");
            alert.setHeaderText("Your account is disabled");
            alert.setContentText("For more information, please contact us via our Email :\n tun.charity@gmail.com");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("False credentials");
            alert.setContentText("Your username or password may be false, Please try again.");
            alert.showAndWait();
        }
        return 0;
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
            pst.setString(1, m.getLogin());
            pst.setString(2, m.getMail());
            pst.setString(3, md5);
            pst.setString(4, m.getfirst_name());
            pst.setString(5, m.getlast_name());
            pst.setString(6, m.getAddress());
            pst.setString(7, m.getCity());
            pst.setString(8, m.getCountry());
            pst.setString(9, m.getPhoto());
            pst.setString(10, m.getRole());

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
            pst.setString(1, m.getLogin());
            pst.setString(2, m.getMail());
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
            pst.setString(13, m.getRole());
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
            pst.setString(1, m.getLogin());
            pst.setString(2, m.getMail());
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
            pst.setString(14, m.getRole());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    // Paperless / Contributor or Entreprise
    @Override
    public ArrayList<Member> getByRole(String role) {
        ArrayList<Member> members = new ArrayList<>();

        if (role.equals("Paperless")) {
            String query = "SELECT * FROM member WHERE role='" + role + "'";
            try {
                pst = connection.prepareStatement(query);
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    members.add(new Member(rs.getString("login"), rs.getString("password"), rs.getString("mail"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getString("photo_path")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (role.equals("Contributor")) {
            String query = "SELECT * FROM member WHERE role='" + role + "'";
            try {
                pst = connection.prepareStatement(query);
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    members.add(new Member(rs.getString("login"), rs.getString("password"), rs.getString("mail"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getString("photo_path"), rs.getString("payment_type"), rs.getString("credit_card_number"), rs.getString("cvv_num")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else //Entreprise case
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

    @Override
    public String getCode(String mail) {
        String query = "SELECT code FROM sendmail where mail LIKE '" + mail + "'";
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //MD5 Encryption password for DataBase
    @Override
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

    @Override
    public void BecomeContributorMember(String type, String ccnumber, String cvv, Member connectedMember) {
        String query = "UPDATE member SET payment_type=?, credit_card_number=? ,cvv_num=?, role=? WHERE id=" + connectedMember.getId();
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, type);
            pst.setString(2, ccnumber);
            pst.setString(3, cvv);
            pst.setString(4, "Contributor");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disableUser(Member m) {
        String query = "UPDATE member SET enable=0 WHERE id=" + m.getId();
        try {
            pst = connection.prepareStatement(query);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getAllLogin() {
        ArrayList<String> logins = new ArrayList<>();
        String query = "SELECT login FROM member";
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            while (rs.next()) {
                logins.add(rs.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logins;
    }

    @Override
    public void updatePassword(String newPwd,int id) {
        String query = "UPDATE member SET password=? WHERE id=" + id;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, newPwd);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePhoto(String name,int id) {
        String query = "UPDATE member SET photo_path=? WHERE id=" + id;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member getUserById(int id){
        String query = "SELECT * FROM member where id= "+id;
        Member m = new Member(0);
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            while (rs.next()) {
                m = new Member(rs.getInt("id"), rs.getString("role"),rs.getString("login"), rs.getString("name"), rs.getString("mail"),
                        rs.getString("password"), rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("address"),rs.getString("city"),rs.getString("country"),
                        rs.getString("payment_type"), rs.getString("credit_card_number"), rs.getString("cvv_num"),
                        rs.getString("president"), rs.getString("foundation_date"), rs.getInt("enable"),rs.getString("photo_path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("m"+m.toString());
        return m;
    }

    public void updateUser(Member m){

        String query = "UPDATE `member` SET `role`=?,`login`=?,`name`=?,`mail`=?,`password`=?,`first_name`=?,`last_name`=?,`Address`=?,`City`=?,`Country`=?," +
                "`payment_type`=?,`credit_card_number`=?,`cvv_num`=?,`president`=?,`foundation_date`=? WHERE id="+m.getId();

        try{
            pst = connection.prepareStatement(query);
            pst.setString(1,m.getRole());
            pst.setString(2,m.getLogin());
            pst.setString(3,m.getName());
            pst.setString(4,m.getMail());
            pst.setString(5,m.getPassword());
            pst.setString(6,m.getfirst_name());
            pst.setString(7,m.getlast_name());
            pst.setString(8,m.getAddress());
            pst.setString(9,m.getCity());
            pst.setString(10,m.getCountry());
            pst.setString(11,m.getPayment_type());
            pst.setString(12,m.getCredit_card_number());
            pst.setString(13,m.getCvv_num());
            pst.setString(14,m.getPresident());
            pst.setString(15,m.getFoundation_date());
            pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Member> getAllMembers() {

        ObservableList<Member> listMembers = FXCollections.observableArrayList();
        try {
            String query = "select * from `member`";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Member member = new Member(rs.getInt("id"), rs.getString("role"), rs.getString("login"), rs.getString("name"), rs.getString("mail"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("city"),rs.getString("country"),rs.getString("payment_type"), rs.getString("credit_card_number"), rs.getString("cvv_num"), rs.getString("president"), rs.getString("foundation_date"), rs.getInt("enable"), rs.getString("photo_path"), rs.getString("register_date"));
                listMembers.add(member);
                System.out.println("size "+ listMembers.size());
            }
        } catch (SQLException e) {
            System.out.println("erreur liste " + e.getMessage());
        }
        return listMembers;
    }
}